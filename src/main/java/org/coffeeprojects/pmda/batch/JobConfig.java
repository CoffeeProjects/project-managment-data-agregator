package org.coffeeprojects.pmda.batch;

import org.coffeeprojects.pmda.issue.IssueUpdateStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {
    @Bean
    protected Step customStep(StepBuilderFactory stepBuilders) {
        return stepBuilders
                .get("issueUpdateStep")
                .tasklet(new IssueUpdateStep())
                .build();
    }

    @Bean
    public Job customJob(JobBuilderFactory jobBuilders, StepBuilderFactory stepBuilders) {
        return jobBuilders
                .get(BatchConstant.JOB_ISSUE_UPDATE)
                .start(customStep(stepBuilders))
                .build();
    }
}