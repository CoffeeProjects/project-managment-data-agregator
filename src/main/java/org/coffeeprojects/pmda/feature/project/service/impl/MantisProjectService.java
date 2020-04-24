package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MantisProjectService implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(MantisProjectService.class);

    public ProjectEntity getProjectById(CompositeIdBaseEntity id) {
        return new ProjectEntity();
    }

    @Transactional
    public void updateProject(ProjectEntity projectEntity) {
        log.debug("Mantis - update project");
    }

    @Transactional
    public void updateLastCheckProject(ProjectEntity projectEntity) {
        log.debug("Mantis - update last check project");
    }

    @Transactional
    public void deactivateProject(TrackerParametersBean tracker) {
        log.debug("Mantis - deactivate project");
    }

    @Transactional
    public ProjectEntity initializeProject(TrackerParametersBean tracker, boolean hasDeactivated) {
        log.debug("Mantis - initialize project");
        return (ProjectEntity) new ProjectEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.MANTIS));
    }
}
