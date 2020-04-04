package org.coffeeprojects.pmda.feature.project.quartz;

import org.coffeeprojects.pmda.batch.BatchConstant;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectUpdateJobConfig {

    @Autowired
    ProjectUpdateStep projectUpdateStep;

    @Bean
    protected Step projectUpdateConfigStep(StepBuilderFactory stepBuilders) {

        return stepBuilders
                .get("projectUpdateStep")
                .tasklet(projectUpdateStep)
                .build();
    }

    @Bean
    public Job projectUpdateJob(JobBuilderFactory jobBuilders, StepBuilderFactory stepBuilders) {
        return jobBuilders
                .get(BatchConstant.JOB_PROJECT_UPDATE)
                .start(projectUpdateConfigStep(stepBuilders))
                .build();
    }
}
