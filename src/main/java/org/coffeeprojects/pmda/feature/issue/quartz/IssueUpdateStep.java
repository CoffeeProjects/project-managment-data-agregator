package org.coffeeprojects.pmda.feature.issue.quartz;

import org.coffeeprojects.pmda.feature.issue.service.MainIssueService;
import org.coffeeprojects.pmda.feature.project.ProjectService;
import org.coffeeprojects.pmda.tool.JobFailingException;
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

    private final Logger logger = LoggerFactory.getLogger(IssueUpdateStep.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MainIssueService mainIssueService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Issue update step initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            logger.info("Issue update step is running ...");
            projectService.getAllProjectsFromDatabase().stream()
                    .filter(p -> p.isActive())
                    .filter(p -> p.getLastCheck() != null)
                    .forEach(p -> {
                        mainIssueService.updateLastModifiedIssues(p, p.getLastCheck());
                    });
        } catch (Exception e) {
            logger.error("Error during the execution of the Issue Update Step");
            throw new JobFailingException("Interruption of Issue Update Step");

        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Issue update step ended.");
        return ExitStatus.COMPLETED;
    }
}
