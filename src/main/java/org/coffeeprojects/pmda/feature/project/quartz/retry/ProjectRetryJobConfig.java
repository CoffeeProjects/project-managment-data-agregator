package org.coffeeprojects.pmda.feature.project.quartz.retry;

import org.coffeeprojects.pmda.batch.BatchEnum;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectRetryJobConfig {

    private ProjectRetryStep projectRetryStep;

    public ProjectRetryJobConfig(ProjectRetryStep projectRetryStep) {
        this.projectRetryStep = projectRetryStep;
    }

    @Bean
    protected Step projectRetryConfigStep(StepBuilderFactory stepBuilders) {

        return stepBuilders
                .get("projectRetryStep")
                .tasklet(projectRetryStep)
                .build();
    }

    @Bean
    public Job projectRetryJob(JobBuilderFactory jobBuilders, StepBuilderFactory stepBuilders) {
        return jobBuilders
                .get(BatchEnum.JOB_PROJECT_RETRY.toString())
                .start(projectRetryConfigStep(stepBuilders))
                .build();
    }
}
