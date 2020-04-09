package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class JiraProjectServiceTest {

    @Mock
    private JiraProjectService jiraProjectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private JiraRepository jiraRepository;

    @Before
    public void setup() {
        jiraProjectService = new JiraProjectService(projectRepository, projectMapper, jiraRepository);
    }

    @Test
    public void test_initialize_project_with_tracker_null() {
        assertThat(jiraProjectService.initializeProject(null)).isNull();
    }

    @Test
    public void test_initialize_project_with_project_already_in_db() {
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity()
            .setTrackerType(TrackerTypeEnum.JIRA)
            .setClientId("1")
            .setTrackerLocalId("1");
        ProjectEntity projectEntity = (ProjectEntity) new ProjectEntity().setId(projectId);

        when(projectRepository.findById(any())).thenReturn(Optional.of(projectEntity));

        assertThat(jiraProjectService.initializeProject(new TrackerParametersBean())).isEqualToComparingFieldByField(projectEntity);
    }
}
