package org.coffeeprojects.pmda.feature.user.service;

import org.coffeeprojects.pmda.feature.user.service.impl.JiraUserService;
import org.coffeeprojects.pmda.feature.user.service.impl.MantisUserService;
import org.coffeeprojects.pmda.feature.user.service.impl.RedmineUserService;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserServiceFactoryTest {

    @Mock
    private JiraUserService jiraUserService;
    @Mock
    private MantisUserService mantisUserService;
    @Mock
    private RedmineUserService redmineUserService;

    private UserServiceFactory userServiceFactory;

    @BeforeEach
    void setup() {
        userServiceFactory = new UserServiceFactory(jiraUserService, mantisUserService, redmineUserService);
    }

    @Test
    void get_service_should_return_user_service() {
        assertThat(userServiceFactory.getService(TrackerType.JIRA)).isEqualTo(jiraUserService);
        assertThat(userServiceFactory.getService(TrackerType.MANTIS)).isEqualTo(mantisUserService);
        assertThat(userServiceFactory.getService(TrackerType.REDMINE)).isEqualTo(redmineUserService);
        assertThat(userServiceFactory.getService(null)).isNull();
    }
}
