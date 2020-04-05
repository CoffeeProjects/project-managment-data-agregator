package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.issue.IssueMapper;
import org.coffeeprojects.pmda.feature.issue.IssueRepository;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.tool.PrefixIdTool;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(value = "tracker.value", havingValue = "jira")
public class JiraIssueService implements IssueService {

    private static final String JIRA_FIELDS = "key,project,issuetype,priority,summary,status,creator,reporter,assignee," +
            "updated,created,duedate,labels,components,issuelinks,fixversions,resolution,customfield_10020";

    private final ProjectRepository projectRepository;

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public JiraIssueService(ProjectRepository projectRepository,
                            IssueRepository issueRepository,
                            IssueMapper issueMapper,
                            JiraRepository jiraRepository) {
        this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    @Override
    public void updateLastModifiedIssues(ProjectEntity projectEntity, Instant fromDate) {
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity, fromDate, JIRA_FIELDS);
        List<IssueEntity> issueEntities = issueJiraBeans.stream().map(issueMapper::toEntity).collect(Collectors.toList());
        PrefixIdTool.fillPrefixIdFromissueEntities(projectEntity, issueEntities);
        try {
            this.issueRepository.saveAll(issueEntities);

            projectEntity.setLastCheck((new Date()).toInstant());
            this.projectRepository.save(projectEntity);
        } catch (Exception e) {

        }
    }
}
