package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;

public interface ProjectService {

    ProjectEntity getProjectById(CompositeIdBaseEntity id);
    void updateProjectByKey(ProjectEntity projectEntity);
}
