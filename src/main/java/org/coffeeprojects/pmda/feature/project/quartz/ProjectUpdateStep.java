package org.coffeeprojects.pmda.feature.project.quartz;

import org.coffeeprojects.pmda.batch.JobFailingException;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.issue.service.IssueServiceFactory;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.feature.project.service.ProjectServiceFactory;
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

@Component
public class ProjectUpdateStep implements Tasklet, StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(ProjectUpdateStep.class);

    TrackerRouter trackerRouter;

    ProjectServiceFactory projectServiceFactory;

    IssueServiceFactory issueServiceFactory;

    public ProjectUpdateStep(TrackerRouter trackerRouter, ProjectServiceFactory projectServiceFactory, IssueServiceFactory issueServiceFactory) {
        this.trackerRouter = trackerRouter;
        this.projectServiceFactory = projectServiceFactory;
        this.issueServiceFactory = issueServiceFactory;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("Project update step initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws JobFailingException {
        try {
            trackerRouter.getTrackerParametersBeans().forEach(tracker -> {
                ProjectService projectService = projectServiceFactory.getService(tracker.getType());
                ProjectEntity projectEntity = projectService.getProjectById(new CompositeIdBaseEntity()
                        .setTrackerType(tracker.getType())
                        .setTrackerLocalId(tracker.getLocalId())
                        .setClientId(tracker.getClientId()));

                if (projectEntity != null && projectEntity.isActive()) {
                    // Update issues
                    IssueService issueService = issueServiceFactory.getService(projectEntity);
                    issueService.updateLastModifiedIssues(projectEntity);

                    // Update project
                    projectService.updateProjectByKey((projectEntity));
                }
            });
        } catch (Exception e) {
            log.error("Error during the execution of the Project Update Step");
            throw new JobFailingException("Interruption of Project Update Step");
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("Project update step ended.");
        return ExitStatus.COMPLETED;
    }
}
