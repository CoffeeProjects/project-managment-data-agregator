package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MantisProjectServiceTest {
    private MantisProjectService mantisProjectService;

    @BeforeEach
    void setUp() {
        mantisProjectService = new MantisProjectService();
    }

    @Test
    void get_project_by_id_should_return_project() {
        assertThrows(UnsupportedOperationException.class, () -> mantisProjectService.getProjectById(new CompositeIdBaseEntity()));
    }

    @Test
    void update_project_should_update() {
        assertThrows(UnsupportedOperationException.class, () -> mantisProjectService.updateProject(new ProjectEntity()));
    }

    @Test
    void update_last_check_project_should_update() {
        assertThrows(UnsupportedOperationException.class, () -> mantisProjectService.updateLastCheckProject(new ProjectEntity()));
    }

    @Test
    void deactivate_project_should_deactivate() {
        assertThrows(UnsupportedOperationException.class, () -> mantisProjectService.deactivateProjectOnError(new TrackerParametersBean(), new RuntimeException()));
    }

}
