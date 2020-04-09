package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.issue.service.impl.JiraIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.MantisIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.RedmineIssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEnum;
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
            if (ProjectEnum.JIRA.equals(projectEntity.getId().getTrackerType())) {
                return jiraIssueService;
            } else if (ProjectEnum.MANTIS.equals(projectEntity.getId().getTrackerType())) {
                return mantisIssueService;
            } else if (ProjectEnum.REDMINE.equals(projectEntity.getId().getTrackerType())) {
                return redmineIssueService;
            }
        }
        return null;
    }
}
