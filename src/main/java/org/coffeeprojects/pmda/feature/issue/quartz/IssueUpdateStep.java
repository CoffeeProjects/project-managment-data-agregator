package org.coffeeprojects.pmda.feature.issue.quartz;

import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.issue.service.IssueServiceFactory;
import org.coffeeprojects.pmda.feature.project.ProjectService;
import org.coffeeprojects.pmda.feature.exception.JobFailingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssueUpdateStep implements Tasklet, StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(IssueUpdateStep.class);

    private ProjectService projectService;

    private IssueServiceFactory issueServiceFactory;

    public IssueUpdateStep(ProjectService projectService, IssueServiceFactory issueServiceFactory) {
        this.projectService = projectService;
        this.issueServiceFactory = issueServiceFactory;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("Issue update step initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            log.info("Issue update step is running ...");
            projectService.getAllProjectsFromDatabase().stream()
                    .filter(p -> p.isActive())
                    .forEach(p -> {
                        IssueService issueService = issueServiceFactory.createIssueService(p);
                        issueService.updateLastModifiedIssues(p);
                    });
        } catch (Exception e) {
            log.error("Error during the execution of the Issue Update Step");
            throw new JobFailingException("Interruption of Issue Update Step");
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("Issue update step ended.");
        return ExitStatus.COMPLETED;
    }
}
