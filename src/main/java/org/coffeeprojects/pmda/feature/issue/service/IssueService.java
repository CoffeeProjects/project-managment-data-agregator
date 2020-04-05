package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;

import java.time.Instant;

public interface IssueService {

    void updateLastModifiedIssues(ProjectEntity projectEntity, Instant fromDate);
}
