package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.feature.project.service.impl.JiraProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.MantisProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.RedmineProjectService;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

    @Mock
    private JiraProjectService jiraProjectService;

    @Mock
    private MantisProjectService mantisProjectService;

    @Mock
    private RedmineProjectService redmineProjectService;

    @Test
    void test_get_service_null() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(null)).isNull();
    }

    @Test
    void test_get_service_with_jira_enum() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(TrackerType.JIRA)).isInstanceOf(JiraProjectService.class);
    }

    @Test
    void test_get_service_with_mantis_enum() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(TrackerType.MANTIS)).isInstanceOf(MantisProjectService.class);
    }

    @Test
    void test_get_service_with_redmine_enum() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(TrackerType.REDMINE)).isInstanceOf(RedmineProjectService.class);
    }
}
