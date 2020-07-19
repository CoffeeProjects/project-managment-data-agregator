package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedmineProjectService implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(RedmineProjectService.class);

    @Transactional(readOnly = true)
    @Override
    public ProjectEntity getProjectById(CompositeIdBaseEntity id) {
        logger.info("Get Redmine project by id: {}", id);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Transactional
    @Override
    public void updateProject(ProjectEntity projectEntity) {
        logger.info("Update Redmine project: {}", projectEntity);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Transactional
    @Override
    public void updateLastCheckProject(ProjectEntity projectEntity) {
        logger.info("Update last check of Redmine project: {}", projectEntity);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Transactional
    @Override
    public void deactivateProject(TrackerParametersBean tracker) {
        logger.info("Deactivate Redmine project: {}", tracker);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Transactional
    @Override
    public ProjectEntity initializeProject(TrackerParametersBean tracker, boolean hasDeactivated) {
        logger.info("Initialize Redmine project: {}, hasDeactivated: {}", tracker, hasDeactivated);
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
