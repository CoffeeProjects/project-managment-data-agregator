package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Clock;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class JiraProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private JiraRepository jiraRepository;

    private Clock clock;

    private JiraProjectService jiraProjectService;


    @BeforeEach
    public void setup() {
        jiraProjectService = new JiraProjectService(projectRepository, projectMapper, jiraRepository, clock);
    }


    @Test
    void get_project_by_id_should_return_project() {
        // Given
        CompositeIdBaseEntity id = new CompositeIdBaseEntity()
                .setClientId("1").setTrackerType(TrackerType.JIRA).setTrackerLocalId("1");

        when(projectRepository.findById(id)).thenReturn(Optional.of(new ProjectEntity()));

        // When
        ProjectEntity projectEntity = jiraProjectService.getProjectById(id);

        // Then
        assertThat(projectEntity).isNotNull();
    }

    @Test
    void update_project_should_update() {
        // Given

        // When

        // Then
    }

    @Test
    void update_last_check_project_should_update() {
        // Given

        // When

        // Then
    }

    @Test
    void deactivate_project_should_deactivate() {
        // Given

        // When

        // Then
    }

    @Test
    void initialize_project_should_initialize() {
        // Given
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity()
                .setTrackerType(TrackerType.JIRA)
                .setClientId("1")
                .setTrackerLocalId("1");
        ProjectEntity projectEntity = new ProjectEntity().setId(projectId);

        when(projectRepository.findById(any())).thenReturn(Optional.of(projectEntity));

        // When
        ProjectEntity actual = jiraProjectService.initializeProject(new TrackerParametersBean(), false);

        // Then
        assertThat(actual).isEqualToComparingFieldByField(projectEntity);
    }
}
