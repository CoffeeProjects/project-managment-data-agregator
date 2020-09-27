package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.CriticalDataException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class JiraProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private JiraRepository jiraRepository;

    private Clock clock;

    private JiraProjectService jiraProjectServiceSpy;

    private JiraProjectService jiraProjectService;

    @BeforeEach
    public void setup() {
        clock = Clock.fixed(LocalDateTime.of(2020, 7, 23, 13, 20).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
        jiraProjectService = new JiraProjectService(projectRepository, projectMapper, jiraRepository, clock);
        jiraProjectServiceSpy = Mockito.spy(jiraProjectService);
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
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity()
                .setTrackerType(TrackerType.JIRA)
                .setClientId("1")
                .setTrackerLocalId("1");

        ProjectEntity projectEntity = new ProjectEntity().setId(projectId);
        ProjectJiraBean projectJiraBean = new ProjectJiraBean().setId(projectId.getClientId());

        when(jiraRepository.getProjectDetails(projectEntity)).thenReturn(projectJiraBean);
        when(projectMapper.toEntity(projectJiraBean)).thenAnswer((args) -> {
            ProjectJiraBean projectJiraBean2 = args.getArgument(0);

            return new ProjectEntity()
                    .setId(new CompositeIdBaseEntity().setClientId(projectJiraBean2.getId()))
                    .setKey(projectJiraBean2.getKey())
                    .setName(projectJiraBean2.getName())
                    .setAdministrator(new UserEntity());
        });

        // When
        jiraProjectService.updateProject(projectEntity);

        // Then
        verify(projectRepository, times(1)).save(projectEntity);
    }

    @Test
    void update_last_check_project_should_update() {
        // Given
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity()
                .setTrackerType(TrackerType.JIRA)
                .setClientId("1")
                .setTrackerLocalId("1");
        ProjectEntity projectEntity = new ProjectEntity().setId(projectId);
        //ReflectionTestUtils.setField(jiraProjectService, "clock", clock);

        // When
        jiraProjectService.updateLastCheckProject(projectEntity);

        // Then
        assertThat(projectEntity.getLastCheck()).isEqualTo(clock.instant());
        verify(projectRepository, times(1)).save(projectEntity);
    }

        @Test
        void deactivate_project_should_deactivate() throws CriticalDataException {
            // Given
            TrackerParametersBean trackerParametersBean = new TrackerParametersBean()
                    .setType(TrackerType.JIRA)
                    .setLocalId("localId")
                    .setClientId("clientId")
                    .setClient("client");
            CompositeIdBaseEntity projectId = new CompositeIdBaseEntity()
                    .setTrackerType(TrackerType.JIRA)
                    .setClientId("1")
                    .setTrackerLocalId("1");
            ProjectEntity projectEntity = new ProjectEntity().setId(projectId);
            RuntimeException runtimeException = new RuntimeException();
            when(projectRepository.save(any())).thenReturn(projectEntity);
            ArgumentCaptor<ProjectEntity> projectEntityArgumentCaptor = ArgumentCaptor.forClass(ProjectEntity.class);

            // When
            jiraProjectServiceSpy.deactivateProjectOnError(trackerParametersBean, runtimeException);

            // Then
            verify(projectRepository).save(projectEntityArgumentCaptor.capture());
            ProjectEntity savedProject = projectEntityArgumentCaptor.getValue();
            assertThat(savedProject.getLastFailureDate()).isNotNull();
            assertThat(savedProject.getFailureCounter()).isEqualTo(1);
            assertThat(savedProject.getLastFailureMessage()).isNotNull();
            assertThat(savedProject.isActive()).isFalse();
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
        ProjectEntity actual = jiraProjectService.initializeProject(new TrackerParametersBean(), false, false);

        // Then
        assertThat(actual).isEqualToComparingFieldByField(projectEntity);
    }

    @Test
    void reactivate_project_should_reactivate() throws CriticalDataException {
        // Given
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity()
                .setTrackerType(TrackerType.JIRA)
                .setClientId("1")
                .setTrackerLocalId("1");
        ProjectEntity projectEntity = new ProjectEntity().setId(projectId);

        // When
        jiraProjectService.reactivateProject(projectEntity);

        // Then
        assertThat(projectEntity.getFailureCounter()).isZero();
        assertThat(projectEntity.getLastFailureDate()).isNull();
        assertThat(projectEntity.getLastFailureMessage()).isNull();
        assertThat(projectEntity.isActive()).isTrue();
        verify(projectRepository, times(1)).save(projectEntity);
    }
}
