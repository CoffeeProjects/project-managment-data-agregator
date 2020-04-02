package org.coffeeprojects.pmda.project.quartz;

import org.coffeeprojects.pmda.batch.BatchConstant;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectUpdateJobConfig {

    @Bean
    protected Step projectUpdateStep(StepBuilderFactory stepBuilders) {
        return stepBuilders
                .get("projectUpdateStep")
                .tasklet(new ProjectUpdateStep())
                .build();
    }

    @Bean
    public Job projectUpdateJob(JobBuilderFactory jobBuilders, StepBuilderFactory stepBuilders) {
        return jobBuilders
                .get(BatchConstant.JOB_PROJECT_UPDATE)
                .start(projectUpdateStep(stepBuilders))
                .build();
    }
}