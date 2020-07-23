package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueCustomField;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.issue.IssueMapper;
import org.coffeeprojects.pmda.feature.issue.IssueRepository;
import org.coffeeprojects.pmda.feature.issue.jirabean.FieldsJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.sprint.SprintEntity;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JiraIssueServiceTest {
    private static final String SPRINT = "com.atlassian.greenhopper.service.sprint.Sprint@2932643f[id=1,name=Sprint1]";

    @Mock
    private IssueRepository issueRepository;
    @Mock
    private IssueMapper issueMapper;
    @Mock
    private JiraRepository jiraRepository;

    @Captor
    private ArgumentCaptor<List<IssueEntity>> issueEntitiesArgumentCaptor;

    private JiraIssueService jiraIssueService;

    @BeforeEach
    void setup() {
        jiraIssueService = new JiraIssueService(issueRepository, issueMapper, jiraRepository);

        when(issueMapper.toEntity(any(IssueJiraBean.class))).thenAnswer((args) -> {
            IssueJiraBean issueJiraBean = args.getArgument(0);

            return new IssueEntity()
                    .setId(new CompositeIdBaseEntity().setClientId(issueJiraBean.getId()))
                    .setKey(issueJiraBean.getKey());
        });
    }

    @Test
    void update_last_modified_issues_should_update_issues() {
        // Given
        ProjectEntity projectEntity = new ProjectEntity()
                .setId(new CompositeIdBaseEntity().setClientId("p1"))
                .setProjectCustomFields(
                        new HashSet<>(Arrays.asList(
                                new ProjectCustomField().setLocalName("SPRINTS").setClientName("SPRINTS"),
                                new ProjectCustomField().setLocalName("custom1key").setClientName("custom1Key"),
                                new ProjectCustomField().setLocalName("custom2key").setClientName("custom2Key")
                        )));

        List<IssueJiraBean> issueJiraBeans = Arrays.asList(
                new IssueJiraBean().setId("i1").setKey("issue1").setFields(new FieldsJiraBean().setCustomFields("SPRINTS", Collections.singletonList(SPRINT)).setCustomFields("custom1Key", "custom1Value")),
                new IssueJiraBean().setId("i2").setKey("issue2").setFields(new FieldsJiraBean().setCustomFields("SPRINTS", Collections.singletonList(SPRINT)).setCustomFields("custom2Key", "custom2Value"))
        );
        when(jiraRepository.getModifiedIssues(any(), any())).thenReturn(issueJiraBeans);

        // When
        jiraIssueService.updateLastModifiedIssues(projectEntity);

        // Then
        verify(issueRepository, times(1)).saveAll(issueEntitiesArgumentCaptor.capture());
        IssueEntity expectedIssueEntity1 = new IssueEntity()
                .setKey("issue1")
                .setId(new CompositeIdBaseEntity().setClientId("i1"))
                .setProject(projectEntity)
                .setSprints(Collections.singleton(new SprintEntity().setName("Sprint1").setId(new CompositeIdBaseEntity().setClientId("1"))))
                .setIssueCustomFields(Collections.singleton(
                        new IssueCustomField().setId(new CompositeIdBaseEntity().setClientId("i1_custom1key")).setValue("custom1Value")
                ));

        IssueEntity expectedIssueEntity2 = new IssueEntity()
                .setKey("issue2")
                .setId(new CompositeIdBaseEntity().setClientId("i2"))
                .setProject(projectEntity)
                .setSprints(Collections.singleton(new SprintEntity().setName("Sprint1").setId(new CompositeIdBaseEntity().setClientId("1"))))
                .setIssueCustomFields(Collections.singleton(
                        new IssueCustomField().setId(new CompositeIdBaseEntity().setClientId("i2_custom2key")).setValue("custom2Value")
                ));

        List<IssueEntity> issueEntities = issueEntitiesArgumentCaptor.getValue();
        assertThat(issueEntities).hasSize(2);
        assertThat(issueEntities.get(0)).isEqualToComparingFieldByField(expectedIssueEntity1);
        assertThat(issueEntities.get(1)).isEqualToComparingFieldByField(expectedIssueEntity2);
    }

    @Test
    void delete_missing_issues_should_delete_issues() {
        // Given
        ProjectEntity projectEntity = new ProjectEntity()
                .setId(new CompositeIdBaseEntity().setClientId("p1"))
                .setProjectCustomFields(
                        new HashSet<>(Arrays.asList(
                                new ProjectCustomField().setLocalName("SPRINTS").setClientName("SPRINTS"),
                                new ProjectCustomField().setLocalName("custom1key").setClientName("custom1Key"),
                                new ProjectCustomField().setLocalName("custom2key").setClientName("custom2Key")
                        )));

        List<IssueEntity> issueEntities = Arrays.asList(
          new IssueEntity().setId(new CompositeIdBaseEntity().setClientId("issue1Id")).setKey("issue1Key"),
          new IssueEntity().setId(new CompositeIdBaseEntity().setClientId("issue2Id")).setKey("issue2Key")
        );
        when(issueRepository.findByProjectAndResolutionDateIsNull(projectEntity)).thenReturn(issueEntities);

        List<IssueJiraBean> issueJiraBeans = Collections.singletonList(
                new IssueJiraBean().setId("issue2Id").setKey("issue2Key")
        );
        when(jiraRepository.getExistingIssues(eq(projectEntity), eq(Arrays.asList("issue1Key", "issue2Key")), anyString()))
                .thenReturn(issueJiraBeans);

        // When
        jiraIssueService.deleteMissingIssues(projectEntity);

        // Then
        verify(issueRepository, times(1)).deleteAll(issueEntitiesArgumentCaptor.capture());
        List<IssueEntity> deletedIssuesEntities = issueEntitiesArgumentCaptor.getValue();
        assertThat(deletedIssuesEntities).hasSize(1);
        assertThat(deletedIssuesEntities.get(0).getKey()).isEqualTo("issue1Key");
    }
}
