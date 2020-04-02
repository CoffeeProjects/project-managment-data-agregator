package org.coffeeprojects.pmda.issue.quartz;

import org.coffeeprojects.pmda.batch.BatchConstant;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IssueUpdateJobConfig {

    @Bean
    protected Step issueUpdateConfigStep(StepBuilderFactory stepBuilders) {
        return stepBuilders
                .get("issueUpdateStep")
                .tasklet(new IssueUpdateStep())
                .build();
    }

    @Bean
    public Job issueUpdateJob(JobBuilderFactory jobBuilders, StepBuilderFactory stepBuilders) {
        return jobBuilders
                .get(BatchConstant.JOB_ISSUE_UPDATE)
                .start(issueUpdateConfigStep(stepBuilders))
                .build();
    }
}