package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.issue.service.impl.JiraIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.MantisIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.RedmineIssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceFactory {

    private final JiraIssueService jiraIssueService;

    private final MantisIssueService mantisIssueService;

    private final RedmineIssueService redmineIssueService;

    public IssueServiceFactory(JiraIssueService jiraIssueService, MantisIssueService mantisIssueService, RedmineIssueService redmineIssueService) {
        this.jiraIssueService = jiraIssueService;
        this.mantisIssueService = mantisIssueService;
        this.redmineIssueService = redmineIssueService;
    }

    public IssueService getService(ProjectEntity projectEntity) {
        if (projectEntity != null && projectEntity.getId() != null) {
            switch (projectEntity.getId().getTrackerType()){
                case JIRA:
                    return jiraIssueService;
                case MANTIS:
                    return mantisIssueService;
                case REDMINE:
                    return redmineIssueService;
            }
        }
        return null;
    }
}
