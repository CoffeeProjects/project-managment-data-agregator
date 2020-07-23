package org.coffeeprojects.pmda.feature.project.quartz.retry;

import org.coffeeprojects.pmda.batch.BatchName;
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
public class ProjectRetryQuartzConfig {

    @Value("${scheduler.trigger.project-retry-cron-expression}")
    private String cronExpression;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean projectRetryJobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ProjectRetryQuartzJobLauncher.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobName", BatchName.JOB_PROJECT_RETRY.toString());
        jobDetailFactoryBean.setJobDataAsMap(map);
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean projectRetryCronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(projectRetryJobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression(this.cronExpression);
        return cronTriggerFactoryBean;
    }

    @Bean(name = "projectRetryScheduler")
    public SchedulerFactoryBean projectSchedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(jobFactory);
        scheduler.setTriggers(projectRetryCronTriggerFactoryBean().getObject());
        scheduler.setApplicationContext(applicationContext);
        return scheduler;
    }
}
