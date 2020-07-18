package org.coffeeprojects.pmda.feature.user.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.InvalidDataException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.feature.user.UserMapper;
import org.coffeeprojects.pmda.feature.user.UserRepository;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JiraUserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private JiraRepository jiraRepository;
    @Mock
    private UserMapper userMapper;

    private JiraUserService jiraUserService;

    @BeforeEach
    void setUp() {
        jiraUserService = new JiraUserService(userRepository, userMapper, jiraRepository);

        when(userMapper.toEntity(any(UserJiraBean.class))).thenAnswer((args) -> {
            UserJiraBean userJiraBean = args.getArgument(0);
            return new UserEntity()
                    .setId(new CompositeIdBaseEntity().setClientId(userJiraBean.getAccountId()))
                    .setDisplayName(userJiraBean.getDisplayName());
        });
    }

    @Test
    void update_should_update_admin_user() {
        // Given
        ProjectEntity projectEntity = new ProjectEntity().setId(
                new CompositeIdBaseEntity().setClientId("123").setTrackerLocalId("99").setTrackerType(TrackerType.JIRA)
        );

        UserJiraBean userJiraBean = new UserJiraBean()
                .setAccountId("1")
                .setDisplayName("Toto");
        when(jiraRepository.getUserDetails(projectEntity)).thenReturn(userJiraBean);

        // When
        jiraUserService.update(projectEntity);

        // Then
        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository, times(1)).save(userEntityArgumentCaptor.capture());

        UserEntity expectedUserEntity = new UserEntity()
                .setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("99").setTrackerType(TrackerType.JIRA))
                .setDisplayName("Toto");
        assertThat(userEntityArgumentCaptor.getValue()).isEqualTo(expectedUserEntity);
    }

    @Test
    void update_when_save_failed_should_throws_exception() {
        // Given
        ProjectEntity projectEntity = new ProjectEntity().setId(
                new CompositeIdBaseEntity().setClientId("123").setTrackerLocalId("99").setTrackerType(TrackerType.JIRA)
        );

        UserJiraBean userJiraBean = new UserJiraBean()
                .setAccountId("1")
                .setDisplayName("Toto");
        when(jiraRepository.getUserDetails(projectEntity)).thenReturn(userJiraBean);

        when(userRepository.save(any(UserEntity.class))).thenThrow(IllegalArgumentException.class);

        // When + Then
        assertThrows(InvalidDataException.class, () -> jiraUserService.update(projectEntity));
    }
}
