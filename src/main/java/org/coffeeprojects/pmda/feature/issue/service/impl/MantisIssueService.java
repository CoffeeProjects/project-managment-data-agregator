package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MantisIssueService implements IssueService {

    private static final Logger log = LoggerFactory.getLogger(MantisIssueService.class);

    @Transactional
    @Override
    public void updateLastModifiedIssues(ProjectEntity projectEntity) {
        log.info("Update from Mantis");
    }

    @Transactional
    @Override
    public void deleteMissingIssues(ProjectEntity projectEntity) {
        log.info("Delete missing issues from Mantis");
    }
}
