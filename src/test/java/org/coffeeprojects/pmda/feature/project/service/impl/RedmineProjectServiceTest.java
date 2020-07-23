package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RedmineProjectServiceTest {
    private RedmineProjectService redmineProjectService;

    @BeforeEach
    void setUp() {
        redmineProjectService = new RedmineProjectService();
    }

    @Test
    void get_project_by_id_should_return_project() {
        assertThrows(UnsupportedOperationException.class, () -> redmineProjectService.getProjectById(new CompositeIdBaseEntity()));
    }

    @Test
    void update_project_should_update() {
        assertThrows(UnsupportedOperationException.class, () -> redmineProjectService.updateProject(new ProjectEntity()));
    }

    @Test
    void update_last_check_project_should_update() {
        assertThrows(UnsupportedOperationException.class, () -> redmineProjectService.updateLastCheckProject(new ProjectEntity()));
    }

    @Test
    void deactivate_project_should_deactivate() {
        assertThrows(UnsupportedOperationException.class, () -> redmineProjectService.deactivateProjectOnError(new TrackerParametersBean(), new RuntimeException()));
    }
}
