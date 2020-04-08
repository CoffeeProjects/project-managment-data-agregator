package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.feature.issue.IssueMapper;
import org.coffeeprojects.pmda.feature.issue.IssueRepository;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
public class MantisIssueService implements IssueService {

    private static final String JIRA_FIELDS = "key,project,issuetype,priority,summary,status,creator,reporter,assignee," +
            "updated,created,duedate,labels,components,issuelinks,fixversions,resolution,customfield_10020";

    private final ProjectRepository projectRepository;

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public MantisIssueService(ProjectRepository projectRepository,
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
    public void updateLastModifiedIssues(ProjectEntity projectEntity) {
        System.out.println("Update from mantis");
    }
}