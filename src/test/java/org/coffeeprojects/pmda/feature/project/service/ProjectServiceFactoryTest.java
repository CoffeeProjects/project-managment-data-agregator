package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.feature.project.service.impl.JiraProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.MantisProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.RedmineProjectService;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ProjectServiceFactoryTest {

    @Mock
    private JiraProjectService jiraProjectService;
    @Mock
    private MantisProjectService mantisProjectService;
    @Mock
    private RedmineProjectService redmineProjectService;

    private ProjectServiceFactory projectServiceFactory;

    @BeforeEach
    void setup() {
        projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
    }

    @Test
    void get_service_should_return_user_service() {
        assertThat(projectServiceFactory.getService(TrackerType.JIRA)).isEqualTo(jiraProjectService);
        assertThat(projectServiceFactory.getService(TrackerType.MANTIS)).isEqualTo(mantisProjectService);
        assertThat(projectServiceFactory.getService(TrackerType.REDMINE)).isEqualTo(redmineProjectService);
        assertThat(projectServiceFactory.getService(null)).isNull();
    }
}
