package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class JiraProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private JiraRepository jiraRepository;

    @Test
    public void test_initialize_project_null() {
        JiraProjectService jiraProjectService = new JiraProjectService(projectRepository, projectMapper, jiraRepository);
        assertThat(jiraProjectService.initializeProject(null)).isNull();
    }

    @Test
    public void test_initialize_project_with_project_null() {
        JiraProjectService jiraProjectService = new JiraProjectService(projectRepository, projectMapper, jiraRepository);
        TrackerParametersBean tracker = new TrackerParametersBean();

        assertThat(jiraProjectService.initializeProject(tracker)).isNull();
    }

    @Test
    @Ignore
    public void test_initialize_project_empty_tracker() {
        JiraProjectService jiraProjectService = new JiraProjectService(projectRepository, projectMapper, jiraRepository);
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity()
            .setTrackerType(TrackerTypeEnum.JIRA)
            .setClientId("1")
            .setTrackerLocalId("1");
        ProjectEntity projectEntity = (ProjectEntity) new ProjectEntity().setId(projectId);
        TrackerParametersBean tracker = new TrackerParametersBean();

        // TODO : Mock à faire fonctionner
        when(jiraProjectService.getProjectById(projectId)).thenReturn(projectEntity);

        assertThat(jiraProjectService.initializeProject(tracker)).isNull();
    }
}
