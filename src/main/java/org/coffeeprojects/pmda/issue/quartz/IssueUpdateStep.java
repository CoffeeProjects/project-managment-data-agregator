package org.coffeeprojects.pmda.issue.quartz;

import org.coffeeprojects.pmda.issue.IssueService;
import org.coffeeprojects.pmda.project.ProjectEntity;
import org.coffeeprojects.pmda.project.ProjectService;
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
    IssueService issueService;

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
                        issueService.updateLastModifiedIssues(projectEntity, projectEntity.getLastCheck().toInstant(),
                                "key,project,issuetype,priority,summary,status,creator,reporter,assignee,updated," +
                                        "created,duedate,labels,components,issuelinks,fixversions,resolution,customfield_10020");
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