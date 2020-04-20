package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;

public interface IssueService {

    void updateLastModifiedIssues(ProjectEntity projectEntity);
    void deleteMissingIssues(ProjectEntity projectEntity);
}
