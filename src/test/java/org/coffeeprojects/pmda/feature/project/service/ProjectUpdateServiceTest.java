package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.exception.CriticalDataException;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.issue.service.IssueServiceFactory;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.service.UserService;
import org.coffeeprojects.pmda.feature.user.service.UserServiceFactory;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectUpdateServiceTest {
    @Mock
    private ProjectServiceFactory projectServiceFactory;
    @Mock
    private UserServiceFactory userServiceFactory;
    @Mock
    private IssueServiceFactory issueServiceFactory;

    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @Mock
    private IssueService issueService;

    private ProjectUpdateService projectUpdateService;


    @BeforeEach
    void setUp() {
        projectUpdateService = new ProjectUpdateService(projectServiceFactory, userServiceFactory, issueServiceFactory);
    }

    @Test
    void update_project_should_update_project() throws CriticalDataException {
        // Given
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean()
                .setType(TrackerType.JIRA)
                .setLocalId("localId")
                .setClientId("clientId")
                .setClient("client");

        ProjectEntity projectEntity = new ProjectEntity().setActive(true);

        when(projectService.initializeProject(trackerParametersBean, false)).thenReturn(projectEntity);

        when(userServiceFactory.getService(TrackerType.JIRA)).thenReturn(userService);
        when(projectServiceFactory.getService(TrackerType.JIRA)).thenReturn(projectService);
        when(issueServiceFactory.getService(TrackerType.JIRA)).thenReturn(issueService);

        // When
        projectUpdateService.updateProject(trackerParametersBean, false);

        // Then
        verify(userService, times(1)).update(projectEntity);
        verify(issueService, times(1)).updateLastModifiedIssues(projectEntity);
        verify(issueService, times(1)).deleteMissingIssues(projectEntity);
        verify(projectService, times(1)).updateLastCheckProject(projectEntity);
        verify(projectService, never()).deactivateProjectOnError(any(), any());
    }

    @Test
    void update_project_should_should_deactivate_project_on_runtime_exception() throws CriticalDataException {
        // Given
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean()
                .setType(TrackerType.JIRA)
                .setLocalId("localId")
                .setClientId("clientId");

        ProjectEntity projectEntity = new ProjectEntity().setActive(true);
        RuntimeException runtimeException = new RuntimeException();

        when(projectService.initializeProject(trackerParametersBean, false)).thenReturn(projectEntity);

        when(projectServiceFactory.getService(TrackerType.JIRA)).thenReturn(projectService);
        when(userServiceFactory.getService(TrackerType.JIRA)).thenReturn(userService);

        doThrow(runtimeException).when(userService).update(projectEntity);

        // When
        projectUpdateService.updateProject(trackerParametersBean, false);

        // Then
        verify(projectService, times(1)).deactivateProjectOnError(trackerParametersBean, runtimeException);
    }
}
