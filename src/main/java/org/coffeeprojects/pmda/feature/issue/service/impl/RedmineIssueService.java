package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedmineIssueService implements IssueService {

    private static final Logger logger = LoggerFactory.getLogger(RedmineIssueService.class);

    @Transactional
    @Override
    public void updateLastModifiedIssues(ProjectEntity projectEntity) {
        logger.info("Update last Redmine modified issues of project: {}", projectEntity);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Transactional
    @Override
    public void deleteMissingIssues(ProjectEntity projectEntity) {
        logger.info("Delete Redmine missing issues of project: {}", projectEntity);
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
