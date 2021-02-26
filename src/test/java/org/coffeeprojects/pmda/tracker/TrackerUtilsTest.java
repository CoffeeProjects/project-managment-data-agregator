package org.coffeeprojects.pmda.tracker;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.changelog.ChangelogEntity;
import org.coffeeprojects.pmda.feature.component.ComponentEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.issuetype.IssueTypeEntity;
import org.coffeeprojects.pmda.feature.priority.PriorityEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.resolution.ResolutionEntity;
import org.coffeeprojects.pmda.feature.sprint.SprintEntity;
import org.coffeeprojects.pmda.feature.status.StatusEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.version.VersionEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TrackerUtilsTest {

    @Test
    void test_fill_ids_from_issue_entities_null() {
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        List<IssueEntity> issueEntities = new ArrayList<>();
        issueEntities.add(issueEntity);

        TrackerUtils.fillIdsFromIssueEntities(null, issueEntities);
        assertThat(issueEntities.get(0).getId().getClientId()).isEqualTo("1");
        assertThat(issueEntities.get(0).getId().getTrackerType()).isNull();
        assertThat(issueEntities.get(0).getId().getTrackerLocalId()).isNull();
    }

    @Test
    void test_fill_ids_from_issue_entity() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));

        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_assignee_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Assignee
        UserEntity assignee = new UserEntity();
        assignee.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setAssignee(assignee);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getAssignee().getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getAssignee().getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_creator_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Creator
        UserEntity creator = new UserEntity();
        creator.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setCreator(creator);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getCreator().getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getCreator().getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_reporter_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Reporter
        UserEntity reporter = new UserEntity();
        reporter.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setReporter(reporter);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getReporter().getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getReporter().getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_status_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Status
        StatusEntity status = new StatusEntity();
        status.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setStatus(status);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getStatus().getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getStatus().getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_resolution_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Resolution
        ResolutionEntity resolution = new ResolutionEntity();
        resolution.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setResolution(resolution);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getResolution().getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getResolution().getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_priority_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Priority
        PriorityEntity priority = new PriorityEntity();
        priority.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setPriority(priority);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getPriority().getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getPriority().getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_issue_type_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // IssueType
        IssueTypeEntity issueType = new IssueTypeEntity();
        issueType.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setIssueType(issueType);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getIssueType().getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
        assertThat(issueEntity.getIssueType().getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    void test_fill_ids_from_issue_entity_with_fix_versions_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Fix versions
        Set<VersionEntity> fixVersions = new HashSet<>();
        VersionEntity fixVersion1 = new VersionEntity();
        fixVersion1.setId(new CompositeIdBaseEntity().setClientId("1"));
        VersionEntity fixVersion2 = new VersionEntity();
        fixVersion2.setId(new CompositeIdBaseEntity().setClientId("1"));
        fixVersions.add(fixVersion1);
        fixVersions.add(fixVersion2);
        issueEntity.setFixVersions(fixVersions);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getFixVersions().forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
                    assertThat(p.getId().getTrackerLocalId()).isEqualTo("1");
                });
    }

    @Test
    void test_fill_ids_from_issue_entity_with_components_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Components
        Set<ComponentEntity> components = new HashSet<>();
        ComponentEntity component1 = new ComponentEntity();
        component1.setId(new CompositeIdBaseEntity().setClientId("1"));
        ComponentEntity component2 = new ComponentEntity();
        component2.setId(new CompositeIdBaseEntity().setClientId("1"));
        components.add(component1);
        components.add(component2);
        issueEntity.setComponents(components);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getComponents().forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
                    assertThat(p.getId().getTrackerLocalId()).isEqualTo("1");
                });
    }

    @Test
    void test_fill_ids_from_issue_entity_with_sprints_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Sprints
        Set<SprintEntity> sprints = new HashSet<>();
        SprintEntity sprint1 = new SprintEntity();
        sprint1.setId(new CompositeIdBaseEntity().setClientId("1"));
        SprintEntity sprint2 = new SprintEntity();
        sprint2.setId(new CompositeIdBaseEntity().setClientId("1"));
        sprints.add(sprint1);
        sprints.add(sprint2);
        issueEntity.setSprints(sprints);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getSprints().forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
                    assertThat(p.getId().getTrackerLocalId()).isEqualTo("1");
                });
    }

    @Test
    void test_fill_ids_from_issue_entity_with_changelog_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);

        // Changelog
        Set<ChangelogEntity> changelogs = new HashSet<>();
        ChangelogEntity changelog = new ChangelogEntity()
                .setId(new CompositeIdBaseEntity().setClientId("1"))
                .setAuthor(new UserEntity().setId(new CompositeIdBaseEntity().setClientId("1")));
        changelogs.add(changelog);
        issueEntity.setChangelog(changelogs);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getChangelog().forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(TrackerType.JIRA);
                    assertThat(p.getId().getTrackerLocalId()).isEqualTo("1");
                });
    }

    @Test
    void test_should_not_fill_ids_from_issue_entity_without_tracker_id_or_tracker_type() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));

        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isNull();
        assertThat(issueEntity.getId().getTrackerLocalId()).isNull();

        projectEntity.getId().setTrackerType(TrackerType.JIRA);
        projectEntity.getId().setTrackerLocalId(null);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isNull();
        assertThat(issueEntity.getId().getTrackerLocalId()).isNull();

        projectEntity.getId().setTrackerType(null);
        projectEntity.getId().setTrackerLocalId("1");
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isNull();
        assertThat(issueEntity.getId().getTrackerLocalId()).isNull();
    }

    @Test
    void test_get_instant_from_timezone_null() {
        assertThat(TrackerUtils.getInstantFromTimezone(null)).isNull();
    }

    @Test
    void test_get_instant_from_bad_timezone() {
        assertThat(TrackerUtils.getInstantFromTimezone("BAD_TIMEZONE")).isNull();
    }
    @Test
    void test_get_instant_from_null_timezone() {
        assertThat(TrackerUtils.getInstantFromTimezone("<null>")).isNull();
    }
}
