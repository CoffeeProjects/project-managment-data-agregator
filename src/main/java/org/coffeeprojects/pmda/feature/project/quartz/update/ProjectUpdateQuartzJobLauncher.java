package org.coffeeprojects.pmda.feature.project.quartz.update;

import org.coffeeprojects.pmda.feature.project.service.impl.JiraProjectService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ProjectUpdateQuartzJobLauncher extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(ProjectUpdateQuartzJobLauncher.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JiraProjectService projectService;

    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            JobLocator jobLocator = applicationContext.getBean(JobLocator.class);
            JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);

            Map<String, Object> jobDataMap = context.getMergedJobDataMap();
            String jobName = (String) jobDataMap.get("jobName");
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            Job job = jobLocator.getJob(jobName);
            JobExecution jobExecution = jobLauncher.run(job, params);

            log.info("########### Status: {}", jobExecution.getStatus());
        } catch (Exception e) {
            log.error("Error during the execution of the Project Update Job >> Details : {}", e.getMessage());
            throw new JobExecutionException("Interruption of Project Update Job");
        }
    }
}
