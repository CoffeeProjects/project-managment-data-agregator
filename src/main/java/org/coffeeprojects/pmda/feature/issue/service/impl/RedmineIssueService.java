package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.feature.issue.IssueMapper;
import org.coffeeprojects.pmda.feature.issue.IssueRepository;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.issue.service.IssuesUpdateParameters;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@ConditionalOnProperty(value = "tracker.value", havingValue = "redmine")
public class RedmineIssueService implements IssueService {

    private final ProjectRepository projectRepository;

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public RedmineIssueService(ProjectRepository projectRepository,
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
    public void updateLastModifiedIssues(ProjectEntity projectEntity, Instant fromDate, IssuesUpdateParameters issuesUpdateParameters) {
        System.out.println("Update from mantis");
    }
}
