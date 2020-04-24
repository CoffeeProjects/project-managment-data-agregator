package org.coffeeprojects.pmda.feature.project.quartz;

import org.coffeeprojects.pmda.batch.JobFailingException;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.issue.service.IssueServiceFactory;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.feature.project.service.ProjectServiceFactory;
import org.coffeeprojects.pmda.feature.user.service.UserService;
import org.coffeeprojects.pmda.feature.user.service.UserServiceFactory;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ProjectUpdateStep implements Tasklet, StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(ProjectUpdateStep.class);

    TrackerRouter trackerRouter;

    ProjectServiceFactory projectServiceFactory;

    UserServiceFactory userServiceFactory;

    IssueServiceFactory issueServiceFactory;

    public ProjectUpdateStep(TrackerRouter trackerRouter, ProjectServiceFactory projectServiceFactory, UserServiceFactory userServiceFactory,
                             IssueServiceFactory issueServiceFactory) {
        this.trackerRouter = trackerRouter;
        this.projectServiceFactory = projectServiceFactory;
        this.userServiceFactory = userServiceFactory;
        this.issueServiceFactory = issueServiceFactory;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("Project update step initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws JobFailingException {

        try {
            for (TrackerParametersBean tracker : trackerRouter.getTrackerParametersBeans()) {
                ProjectService projectService = projectServiceFactory.getService(tracker.getType());
                try {
                    this.updateProjectElements(projectService, tracker);
                } catch (RuntimeException e) {
                    projectService.deactivateProject(tracker);
                    log.error("Error during script execution with this tracker: {}. Please check the errors before reactivating it in the database. Error details => {}", tracker, e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new JobFailingException("Interruption of project update => " + e.getMessage());
        }
        return RepeatStatus.FINISHED;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateProjectElements(ProjectService projectService, TrackerParametersBean tracker) {
        ProjectEntity projectEntity = projectService.initializeProject(tracker, false);
        if (Boolean.TRUE.equals(projectEntity.isActive())) {
            // Update administrator account
            UserService userService = userServiceFactory.getService(tracker.getType());
            userService.update(projectEntity);

            // Update issues
            IssueService issueService = issueServiceFactory.getService(projectEntity);
            issueService.updateLastModifiedIssues(projectEntity);

            // Delete missing issues
            issueService.deleteMissingIssues(projectEntity);

            // Update last check project
            projectService.updateLastCheckProject(projectEntity);
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("Project update step ended.");
        return ExitStatus.COMPLETED;
    }
}
