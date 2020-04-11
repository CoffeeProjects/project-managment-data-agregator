package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.feature.project.service.impl.JiraProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.MantisProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.RedmineProjectService;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class ProjectServiceTest {

    @Mock
    private JiraProjectService jiraProjectService;

    @Mock
    private MantisProjectService mantisProjectService;

    @Mock
    private RedmineProjectService redmineProjectService;

    @Test
    public void test_get_service_null() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(null)).isNull();
    }

    @Test
    public void test_get_service_bad_jira_enum() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(TrackerTypeEnum.JIRA)).isInstanceOf(JiraProjectService.class);
    }

    @Test
    public void test_get_service_bad_mantis_enum() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(TrackerTypeEnum.MANTIS)).isInstanceOf(MantisProjectService.class);
    }

    @Test
    public void test_get_service_bad_redmine_enum() {
        ProjectServiceFactory projectServiceFactory = new ProjectServiceFactory(jiraProjectService, mantisProjectService, redmineProjectService);
        assertThat(projectServiceFactory.getService(TrackerTypeEnum.REDMINE)).isInstanceOf(RedmineProjectService.class);
    }
}
