package org.coffeeprojects.pmda.feature.issue.quartz;

import org.coffeeprojects.pmda.feature.issue.service.IssuesUpdateParameters;
import org.coffeeprojects.pmda.feature.issue.service.MainIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.JiraIssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IssueUpdateStep implements Tasklet, StepExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(IssueUpdateStep.class);

    @Autowired
    ProjectService projectService;

    @Autowired
    MainIssueService mainIssueService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("Issue update step initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            logger.info("Issue update step is running ...");
            List<ProjectEntity> projectEntities = projectService.getAllProjectsFromDatabase();
            for (ProjectEntity projectEntity: projectEntities) {
                if (projectEntity.isActive()) {
                    if (projectEntity.getLastCheck() != null) {
                        String fields = "key,project,issuetype,priority,summary,status,creator,reporter,assignee,updated," +
                                "created,duedate,labels,components,issuelinks,fixversions,resolution,customfield_10020";
                        mainIssueService.updateLastModifiedIssues(projectEntity, projectEntity.getLastCheck().toInstant(),
                                new IssuesUpdateParameters().setFields(fields));
                    } else {
                        logger.error("Please enter a checked date for the " + projectEntity.getKey() + " project");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Issue update step ended.");
        return ExitStatus.COMPLETED;
    }
}
