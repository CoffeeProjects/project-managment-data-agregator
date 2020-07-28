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
public class MantisProjectService implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(MantisProjectService.class);
    private static final String NOT_YET_IMPLEMENTED = "Mantis is not yet implemented";

    @Override
    public ProjectEntity getProjectById(CompositeIdBaseEntity id) {
        logger.info("Get Mantis project by id: {}", id);
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Transactional
    @Override
    public void updateProject(ProjectEntity projectEntity) {
        logger.info("Update Mantis project: {}", projectEntity);
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Transactional
    @Override
    public void updateLastCheckProject(ProjectEntity projectEntity) {
        logger.info("Update last check of Mantis project: {}", projectEntity);
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Transactional
    @Override
    public void deactivateProjectOnError(TrackerParametersBean tracker, RuntimeException e) {
        logger.info("Deactivate Mantis project: {}", tracker);
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Transactional
    @Override
    public void reactivateProject(ProjectEntity projectEntity) {
        logger.info("Reactivate Mantis project: {}", projectEntity);
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }

    @Transactional
    @Override
    public ProjectEntity initializeProject(TrackerParametersBean tracker, boolean forceRetry, boolean hasDeactivated) {
        logger.info("Initialize Mantis project: {}, hasDeactivated: {}", tracker, hasDeactivated);
        throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
    }
}
