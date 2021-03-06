package org.coffeeprojects.pmda.feature.project.quartz.update;

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
public class ProjectUpdateQuartzConfig {

    @Value("${scheduler.trigger.project-update-cron-expression}")
    private String cronExpression;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public JobDetailFactoryBean projectUpdateJobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ProjectUpdateQuartzJobLauncher.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobName", BatchName.JOB_PROJECT_UPDATE.toString());
        jobDetailFactoryBean.setJobDataAsMap(map);
        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean projectUpdateCronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(projectUpdateJobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression(this.cronExpression);
        return cronTriggerFactoryBean;
    }

    @Bean(name = "projectUpdateScheduler")
    public SchedulerFactoryBean projectSchedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(jobFactory);
        scheduler.setTriggers(projectUpdateCronTriggerFactoryBean().getObject());
        scheduler.setApplicationContext(applicationContext);
        return scheduler;
    }
}
