package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.service.impl.MantisProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RedmineIssueService implements IssueService {

    private static final Logger log = LoggerFactory.getLogger(MantisProjectService.class);

    @Transactional
    @Override
    public void updateLastModifiedIssues(ProjectEntity projectEntity) {
        log.info("Update from Redmine");
    }

    @Transactional
    @Override
    public void deleteMissingIssues(ProjectEntity projectEntity) {
        log.info("Delete missing issues from Redmine");
    }
}
