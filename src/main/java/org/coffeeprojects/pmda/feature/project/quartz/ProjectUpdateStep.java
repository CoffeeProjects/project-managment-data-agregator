package org.coffeeprojects.pmda.feature.project.quartz;

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
public class ProjectUpdateStep implements Tasklet, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(ProjectUpdateStep.class);

    private ProjectService projectService;

    public ProjectUpdateStep(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Project update step initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws JobFailingException {
        try {
            projectService.getAllProjectsFromDatabase().stream()
                    .filter(p -> p.isActive())
                    .forEach(p -> {
                        projectService.updateProjectByKey(p);
                    });
        } catch (Exception e) {
            logger.error("Error during the execution of the Project Update Step");
            throw new JobFailingException("Interruption of Project Update Step");
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Project update step ended.");
        return ExitStatus.COMPLETED;
    }
}
