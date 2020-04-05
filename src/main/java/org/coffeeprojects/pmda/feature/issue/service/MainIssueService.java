package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MainIssueService {

    private final IssueService issueService;

    public MainIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    public void updateLastModifiedIssues(ProjectEntity projectEntity, Instant fromDate) {
        this.issueService.updateLastModifiedIssues(projectEntity, fromDate);
    }
}
