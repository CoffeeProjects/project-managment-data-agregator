package org.coffeeprojects.pmda.feature.user.service;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;

public interface UserService {

    /**
     * Update the user admin of a project
     *
     * @param projectEntity The project
     */
    void update(ProjectEntity projectEntity);
}
