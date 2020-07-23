package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;

public interface IssueService {

    /**
     * Update last modified issues of a project
     *
     * @param projectEntity The project
     */
    void updateLastModifiedIssues(ProjectEntity projectEntity);

    /**
     * Delete missing issues of a project
     *
     * @param projectEntity The project
     */
    void deleteMissingIssues(ProjectEntity projectEntity);
}
