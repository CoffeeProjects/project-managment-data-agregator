package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.CriticalDataException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;

public interface ProjectService {

    /**
     * Get a project by id
     *
     * @param id The id
     * @return The project or null
     */
    ProjectEntity getProjectById(CompositeIdBaseEntity id);

    /**
     * Update a project
     *
     * @param projectEntity The project to update
     */
    void updateProject(ProjectEntity projectEntity);

    /**
     * Update last check date of a project
     *
     * @param projectEntity The project
     */
    void updateLastCheckProject(ProjectEntity projectEntity);

    /**
     * Deactivate a project
     *
     * @param tracker The tracker data
     * @throws CriticalDataException When the project cannot be deactivate
     */
    void deactivateProjectOnError(TrackerParametersBean tracker, RuntimeException e) throws CriticalDataException;

    /**
     * Deactivate a project
     *
     * @param projectEntity The project
     * @throws CriticalDataException When the project cannot be deactivate
     */
    void reactivateProject(ProjectEntity projectEntity) throws CriticalDataException;

    /**
     * Initialize a project
     *
     * @param tracker         The tracker data
     * @param forceDeactivate True if the project must no be updated ?? TODO: A verifier
     * @return The updated project
     */
    ProjectEntity initializeProject(TrackerParametersBean tracker, boolean forceRetry, boolean forceDeactivate);
}
