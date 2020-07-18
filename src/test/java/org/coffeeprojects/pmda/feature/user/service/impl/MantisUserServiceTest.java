package org.coffeeprojects.pmda.feature.user.service.impl;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MantisUserServiceTest {

    private MantisUserService mantisUserService;

    @BeforeEach
    void setUp() {
        mantisUserService = new MantisUserService();
    }

    @Test
    void update_should_update_admin_user() {
        assertThrows(UnsupportedOperationException.class, () -> mantisUserService.update(new ProjectEntity()));
    }
}
