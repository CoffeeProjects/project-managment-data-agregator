package org.coffeeprojects.pmda.feature.project.quartz.update;

import org.coffeeprojects.pmda.batch.BatchEnum;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectUpdateJobConfig {

    private ProjectUpdateStep projectUpdateStep;

    public ProjectUpdateJobConfig(ProjectUpdateStep projectUpdateStep) {
        this.projectUpdateStep = projectUpdateStep;
    }

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
                .get(BatchEnum.JOB_PROJECT_UPDATE.toString())
                .start(projectUpdateConfigStep(stepBuilders))
                .build();
    }
}
