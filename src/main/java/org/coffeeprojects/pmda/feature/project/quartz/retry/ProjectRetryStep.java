package org.coffeeprojects.pmda.feature.project.quartz.retry;

import org.coffeeprojects.pmda.batch.JobFailingException;
import org.coffeeprojects.pmda.exception.ExceptionConstant;
import org.coffeeprojects.pmda.feature.project.service.ProjectUpdateService;
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

@Component
public class ProjectRetryStep implements Tasklet, StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(ProjectRetryStep.class);

    TrackerRouter trackerRouter;

    ProjectUpdateService projectUpdateService;

    public ProjectRetryStep(TrackerRouter trackerRouter, ProjectUpdateService projectUpdateService) {
        this.trackerRouter = trackerRouter;
        this.projectUpdateService = projectUpdateService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("Project retry step initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws JobFailingException {
        try {
            for (TrackerParametersBean tracker : trackerRouter.getTrackerParametersBeans()) {
                projectUpdateService.updateProject(tracker, true);
            }
        } catch (Exception e) {
            throw new JobFailingException(ExceptionConstant.ERROR_STOP_PROJECT_RETRY + e.getMessage(), e);
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("Project retry step ended.");
        return ExitStatus.COMPLETED;
    }
}
