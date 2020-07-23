package org.coffeeprojects.pmda.feature.user.service.impl;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RedmineUserServiceTest {

    private RedmineUserService redmineUserService;

    @BeforeEach
    void setUp() {
        redmineUserService = new RedmineUserService();
    }

    @Test
    void update_should_update_admin_user() {
        assertThrows(UnsupportedOperationException.class, () -> redmineUserService.update(new ProjectEntity()));
    }
}
