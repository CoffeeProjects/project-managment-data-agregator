package org.coffeeprojects.pmda.feature.issue.quartz;

import org.coffeeprojects.pmda.batch.BatchConstant;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnExpression("'${scheduler.enabled}'=='true'")
public class IssueUpdateQuartzConfig {

    @Value("${scheduler.trigger.issue-update-cron-expression}")
    private String cronExpression;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(IssueUpdateQuartzJobLauncher.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobName", BatchConstant.JOB_ISSUE_UPDATE);
        jobDetailFactoryBean.setJobDataAsMap(map);
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression(this.cronExpression);
        return cronTriggerFactoryBean;
    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(jobFactory);
        scheduler.setTriggers(cronTriggerFactoryBean().getObject());
        scheduler.setApplicationContext(applicationContext);
        return scheduler;
    }
}
