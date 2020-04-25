package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.CriticalDataException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;

public interface ProjectService {

    ProjectEntity getProjectById(CompositeIdBaseEntity id);
    void updateProject(ProjectEntity projectEntity);
    void updateLastCheckProject(ProjectEntity projectEntity);
    void deactivateProject(TrackerParametersBean tracker) throws CriticalDataException;
    ProjectEntity initializeProject(TrackerParametersBean tracker, boolean forceDeactivate);
}
