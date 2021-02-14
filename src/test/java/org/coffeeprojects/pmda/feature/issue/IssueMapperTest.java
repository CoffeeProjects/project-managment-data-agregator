package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.changelog.ChangelogEntity;
import org.coffeeprojects.pmda.feature.changelog.jirabean.ChangelogJiraBean;
import org.coffeeprojects.pmda.feature.changelog.jirabean.HistoryJiraBean;
import org.coffeeprojects.pmda.feature.changelog.jirabean.ItemHistoryJiraBean;
import org.coffeeprojects.pmda.feature.component.ComponentEntity;
import org.coffeeprojects.pmda.feature.component.ComponentJiraBean;
import org.coffeeprojects.pmda.feature.component.ComponentMapperImpl;
import org.coffeeprojects.pmda.feature.issue.jirabean.FieldsJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.TimeTrackingJiraBean;
import org.coffeeprojects.pmda.feature.issuetype.IssueTypeEntity;
import org.coffeeprojects.pmda.feature.issuetype.IssueTypeJiraBean;
import org.coffeeprojects.pmda.feature.issuetype.IssueTypeMapperImpl;
import org.coffeeprojects.pmda.feature.priority.PriorityEntity;
import org.coffeeprojects.pmda.feature.priority.PriorityJiraBean;
import org.coffeeprojects.pmda.feature.priority.PriorityMapperImpl;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectMapperImpl;
import org.coffeeprojects.pmda.feature.resolution.ResolutionEntity;
import org.coffeeprojects.pmda.feature.resolution.ResolutionJiraBean;
import org.coffeeprojects.pmda.feature.resolution.ResolutionMapperImpl;
import org.coffeeprojects.pmda.feature.sprint.SprintMapperImpl;
import org.coffeeprojects.pmda.feature.status.StatusMapperImpl;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.feature.user.UserMapperImpl;
import org.coffeeprojects.pmda.feature.version.VersionEntity;
import org.coffeeprojects.pmda.feature.version.VersionJiraBean;
import org.coffeeprojects.pmda.feature.version.VersionMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {UserMapperImpl.class, StatusMapperImpl.class, ResolutionMapperImpl.class,
        PriorityMapperImpl.class, IssueTypeMapperImpl.class, ProjectMapperImpl.class, VersionMapperImpl.class,
        ComponentMapperImpl.class, SprintMapperImpl.class, IssueMapperImpl.class})
@ExtendWith(SpringExtension.class)
class IssueMapperTest {
    @Autowired
    private IssueMapper issueMapper;

    @Test
    void to_entity_should_map_issue_jira_bean_to_issue_entity() {
        // Given
        Date dateCreated = new Date();
        HistoryJiraBean historyJiraBean = new HistoryJiraBean().setId("changelog1").setCreated(dateCreated).setItems(Collections.singleton(new ItemHistoryJiraBean())).setAuthor(new UserJiraBean());
        ChangelogJiraBean changelogJiraBean = new ChangelogJiraBean().setHistories(Collections.singleton(historyJiraBean));
        IssueJiraBean issueJiraBean = new IssueJiraBean()
                .setId("id")
                .setKey("key1")
                .setChangelog(changelogJiraBean)
                .setFields(
                        new FieldsJiraBean()
                                .setSummary("summary")
                                .setLabels(Arrays.asList("test-label", "label2"))
                                .setAssignee(new UserJiraBean().setAccountId("assigneeUserId"))
                                .setReporter(new UserJiraBean().setAccountId("reporterUserId"))
                                .setCreator(new UserJiraBean().setAccountId("creatorUserId"))
                                .setResolution(new ResolutionJiraBean().setId("resolutionId"))
                                .setPriority(new PriorityJiraBean().setId("priorityId"))
                                .setIssueType(new IssueTypeJiraBean().setId("issueTypeId"))
                                .setProject(new ProjectJiraBean().setId("projectId"))
                                .setFixVersions(Collections.singleton(new VersionJiraBean().setId("versionId")))
                                .setTimeTracking(new TimeTrackingJiraBean().setTimeSpentSeconds(1).setOriginalEstimateSeconds(2).setRemainingEstimateSeconds(3))
                                .setComponents(Collections.singleton(new ComponentJiraBean().setId("componentId")))
                );

        // When
        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        IssueEntity expectedIssueEntity = new IssueEntity()
                .setId(new CompositeIdBaseEntity().setClientId("id"))
                .setKey("key1")
                .setSummary("summary")
                .setLabels(Arrays.asList("test-label", "label2"))
                .setAssignee(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("assigneeUserId")))
                .setReporter(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("reporterUserId")))
                .setCreator(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("creatorUserId")))
                .setResolution(new ResolutionEntity().setId(new CompositeIdBaseEntity().setClientId("resolutionId")))
                .setPriority(new PriorityEntity().setId(new CompositeIdBaseEntity().setClientId("priorityId")))
                .setIssueType(new IssueTypeEntity().setId(new CompositeIdBaseEntity().setClientId("issueTypeId")))
                .setProject(new ProjectEntity().setId(new CompositeIdBaseEntity().setClientId("projectId")))
                .setFixVersions(Collections.singleton(new VersionEntity().setId(new CompositeIdBaseEntity().setClientId("versionId"))))
                .setTimeSpentSeconds(1)
                .setOriginalEstimateSeconds(2)
                .setRemainingEstimateSeconds(3)
                .setComponents(Collections.singleton(new ComponentEntity().setId(new CompositeIdBaseEntity().setClientId("componentId"))))
                .setChangelog(Collections.singleton(new ChangelogEntity()
                        .setId(new CompositeIdBaseEntity().setClientId("changelog1_" + formatter.format(dateCreated) + "_0"))
                        .setAuthor(new UserEntity().setId(new CompositeIdBaseEntity()).setActive(false))
                        .setCreated(dateCreated)
                ));

        assertThat(issueEntity).isEqualToComparingFieldByField(expectedIssueEntity);
    }

    @Test
    void to_entity_should_map_issue_jira_bean_to_issue_entity_changelog_field() {
        // Given
        Date dateCreated = new Date();

        Set<HistoryJiraBean> historyJiraBeans = new HashSet<>();

        Set<ItemHistoryJiraBean> items1 = new HashSet<>();
        items1.add(new ItemHistoryJiraBean().setField("field").setFieldType("fieldType").setFieldId("fieldId").setFrom("from").setFromString("fromStringItem1").setTo("to").setToString("toStringItem1"));
        items1.add(new ItemHistoryJiraBean().setField("field").setFieldType("fieldType").setFieldId("fieldId").setFrom("from").setFromString("fromStringItem2").setTo("to").setToString("toStringItem2"));

        Set<ItemHistoryJiraBean> items2 = new HashSet<>();
        items2.add(new ItemHistoryJiraBean().setField("field").setFieldType("fieldType").setFieldId("fieldId").setFrom("from").setFromString("fromString2_1").setTo("to").setToString("toString2_1"));

        historyJiraBeans.add(new HistoryJiraBean().setId("changelog1").setCreated(dateCreated).setItems(items1).setAuthor(new UserJiraBean().setAccountId("userAccount")));
        historyJiraBeans.add(new HistoryJiraBean().setId("changelog2").setCreated(dateCreated).setItems(items2).setAuthor(new UserJiraBean().setAccountId("userAccount")));

        ChangelogJiraBean changelogJiraBean = new ChangelogJiraBean().setHistories(historyJiraBeans);

        IssueJiraBean issueJiraBean = new IssueJiraBean()
                .setId("id")
                .setKey("key1")
                .setChangelog(changelogJiraBean)
                .setFields(
                        new FieldsJiraBean()
                                .setSummary("summary")
                                .setLabels(Arrays.asList("test-label", "label2"))
                                .setAssignee(new UserJiraBean().setAccountId("assigneeUserId"))
                                .setReporter(new UserJiraBean().setAccountId("reporterUserId"))
                                .setCreator(new UserJiraBean().setAccountId("creatorUserId"))
                                .setResolution(new ResolutionJiraBean().setId("resolutionId"))
                                .setPriority(new PriorityJiraBean().setId("priorityId"))
                                .setIssueType(new IssueTypeJiraBean().setId("issueTypeId"))
                                .setProject(new ProjectJiraBean().setId("projectId"))
                                .setFixVersions(Collections.singleton(new VersionJiraBean().setId("versionId")))
                                .setTimeTracking(new TimeTrackingJiraBean().setTimeSpentSeconds(1).setOriginalEstimateSeconds(2).setRemainingEstimateSeconds(3))
                                .setComponents(Collections.singleton(new ComponentJiraBean().setId("componentId")))
                );

        // When
        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Set<ChangelogEntity> changelogEntities = new HashSet<>();
        String fromStringChangelog1_1 = issueEntity.getChangelog().stream().filter(p -> p.getId().getClientId().equals("changelog1_" + formatter.format(dateCreated) + "_0")).findFirst().get().getFromString();
        String toStringChangelog1_1 = issueEntity.getChangelog().stream().filter(p -> p.getId().getClientId().equals("changelog1_" + formatter.format(dateCreated) + "_0")).findFirst().get().getToString();
        String fromStringChangelog1_2 = issueEntity.getChangelog().stream().filter(p -> p.getId().getClientId().equals("changelog1_" + formatter.format(dateCreated) + "_1")).findFirst().get().getFromString();
        String toStringChangelog1_2 = issueEntity.getChangelog().stream().filter(p -> p.getId().getClientId().equals("changelog1_" + formatter.format(dateCreated) + "_1")).findFirst().get().getToString();

        changelogEntities.add(new ChangelogEntity().setId(new CompositeIdBaseEntity().setClientId("changelog1_" + formatter.format(dateCreated) + "_0"))
                .setCreated(dateCreated)
                .setField("field").setFieldType("fieldType").setFieldId("fieldId").setFromString(fromStringChangelog1_1).setToString(toStringChangelog1_1)
                .setAuthor(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("userAccount")).setActive(false)));
        changelogEntities.add(new ChangelogEntity().setId(new CompositeIdBaseEntity().setClientId("changelog1_" + formatter.format(dateCreated) + "_1"))
                .setCreated(dateCreated)
                .setField("field").setFieldType("fieldType").setFieldId("fieldId").setFromString(fromStringChangelog1_2).setToString(toStringChangelog1_2)
                .setAuthor(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("userAccount")).setActive(false)));
        changelogEntities.add(new ChangelogEntity().setId(new CompositeIdBaseEntity().setClientId("changelog2_" + formatter.format(dateCreated) + "_0"))
                .setCreated(dateCreated)
                .setField("field").setFieldType("fieldType").setFieldId("fieldId").setFromString("fromString2_1").setToString("toString2_1")
                .setAuthor(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("userAccount")).setActive(false)));

        IssueEntity expectedIssueEntity = new IssueEntity()
                .setId(new CompositeIdBaseEntity().setClientId("id"))
                .setKey("key1")
                .setSummary("summary")
                .setLabels(Arrays.asList("test-label", "label2"))
                .setAssignee(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("assigneeUserId")))
                .setReporter(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("reporterUserId")))
                .setCreator(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("creatorUserId")))
                .setResolution(new ResolutionEntity().setId(new CompositeIdBaseEntity().setClientId("resolutionId")))
                .setPriority(new PriorityEntity().setId(new CompositeIdBaseEntity().setClientId("priorityId")))
                .setIssueType(new IssueTypeEntity().setId(new CompositeIdBaseEntity().setClientId("issueTypeId")))
                .setProject(new ProjectEntity().setId(new CompositeIdBaseEntity().setClientId("projectId")))
                .setFixVersions(Collections.singleton(new VersionEntity().setId(new CompositeIdBaseEntity().setClientId("versionId"))))
                .setTimeSpentSeconds(1)
                .setOriginalEstimateSeconds(2)
                .setRemainingEstimateSeconds(3)
                .setComponents(Collections.singleton(new ComponentEntity().setId(new CompositeIdBaseEntity().setClientId("componentId"))))
                .setChangelog(changelogEntities);

        assertThat(issueEntity).isEqualToComparingFieldByField(expectedIssueEntity);
    }

    @Test
    void to_entity_should_map_issue_jira_bean_null() {
        // When
        IssueEntity issueEntity = issueMapper.toEntity(null);
        assertThat(issueEntity).isNull();
    }

    @Test
    void to_entity_should_map_issue_jira_bean_with_all_fields_null() {
        // Given
        IssueJiraBean issueJiraBean = new IssueJiraBean();

        // When
        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        IssueEntity expectedIssueEntity = new IssueEntity()
                .setId(new CompositeIdBaseEntity())
                .setChangelog(new HashSet<>());

        assertThat(issueEntity).isEqualToComparingFieldByField(expectedIssueEntity);
    }
}
