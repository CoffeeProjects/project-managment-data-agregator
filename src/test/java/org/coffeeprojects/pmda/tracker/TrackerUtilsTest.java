package org.coffeeprojects.pmda.tracker;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.component.ComponentEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.issueType.IssueTypeEntity;
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

public class TrackerUtilsTest {

    @Test
    public void test_fill_ids_from_issue_entities_null() {
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        List<IssueEntity> issueEntities = new ArrayList();
        issueEntities.add(issueEntity);

        TrackerUtils.fillIdsFromIssueEntities(null, issueEntities);
        assertThat(issueEntities.get(0).getId().getClientId()).isEqualTo("1");
        assertThat(issueEntities.get(0).getId().getTrackerType()).isNull();
        assertThat(issueEntities.get(0).getId().getTrackerLocalId()).isNull();
    }

    @Test
    public void test_fill_ids_from_issue_entity_field_by_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerTypeEnum.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));


        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getId().getTrackerLocalId()).isEqualTo("1");

        // Assignee
        UserEntity assignee = new UserEntity();
        assignee.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setAssignee(assignee);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getAssignee().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getAssignee().getId().getTrackerLocalId()).isEqualTo("1");

        // Creator
        UserEntity creator = new UserEntity();
        creator.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setCreator(creator);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getCreator().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getCreator().getId().getTrackerLocalId()).isEqualTo("1");

        // Reporter
        UserEntity reporter = new UserEntity();
        reporter.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setReporter(reporter);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getReporter().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getReporter().getId().getTrackerLocalId()).isEqualTo("1");

        // Status
        StatusEntity status = new StatusEntity();
        status.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setStatus(status);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getStatus().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getStatus().getId().getTrackerLocalId()).isEqualTo("1");

        // Resolution
        ResolutionEntity resolution = new ResolutionEntity();
        resolution.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setResolution(resolution);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getResolution().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getResolution().getId().getTrackerLocalId()).isEqualTo("1");

        // Priority
        PriorityEntity priority = new PriorityEntity();
        priority.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setPriority(priority);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getPriority().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getPriority().getId().getTrackerLocalId()).isEqualTo("1");

        // Priority
        IssueTypeEntity issueType = new IssueTypeEntity();
        issueType.setId(new CompositeIdBaseEntity().setClientId("1"));
        issueEntity.setIssueType(issueType);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getIssueType().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getIssueType().getId().getTrackerLocalId()).isEqualTo("1");

        // Priority
        issueEntity.setProject(projectEntity);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getProject().getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getProject().getId().getTrackerLocalId()).isEqualTo("1");

        // Fix versions
        Set<VersionEntity> fixVersions = new HashSet();
        VersionEntity fixVersion1 = new VersionEntity();
        fixVersion1.setId(new CompositeIdBaseEntity().setClientId("1"));
        VersionEntity fixVersion2 = new VersionEntity();
        fixVersion2.setId(new CompositeIdBaseEntity().setClientId("1"));
        fixVersions.add(fixVersion1);
        fixVersions.add(fixVersion2);
        issueEntity.setFixVersions(fixVersions);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getFixVersions().stream()
                .forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
                    assertThat(p.getId().getTrackerLocalId()).isEqualTo("1");
                });

        // Components
        Set<ComponentEntity> components = new HashSet();
        ComponentEntity component1 = new ComponentEntity();
        component1.setId(new CompositeIdBaseEntity().setClientId("1"));
        ComponentEntity component2 = new ComponentEntity();
        component2.setId(new CompositeIdBaseEntity().setClientId("1"));
        components.add(component1);
        components.add(component2);
        issueEntity.setComponents(components);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getComponents().stream()
                .forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
                    assertThat(p.getId().getTrackerLocalId()).isEqualTo("1");
                });

        // Sprints
        Set<SprintEntity> sprints = new HashSet();
        SprintEntity sprint1 = new SprintEntity();
        sprint1.setId(new CompositeIdBaseEntity().setClientId("1"));
        SprintEntity sprint2 = new SprintEntity();
        sprint2.setId(new CompositeIdBaseEntity().setClientId("1"));
        sprints.add(sprint1);
        sprints.add(sprint2);
        issueEntity.setSprints(sprints);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getSprints().stream()
                .forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
                    assertThat(p.getId().getTrackerLocalId()).isEqualTo("1");
                });
    }

    @Test
    public void test_fill_ids_from_issue_entity() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerTypeEnum.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));

        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isEqualTo(TrackerTypeEnum.JIRA);
        assertThat(issueEntity.getId().getTrackerLocalId()).isEqualTo("1");
    }

    @Test
    public void test_fill_ids_from_issue_entity_without_tracker_id_or_tracker_type() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setClientId("1"));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setClientId("1"));

        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isNull();
        assertThat(issueEntity.getId().getTrackerLocalId()).isNull();

        projectEntity.getId().setTrackerType(TrackerTypeEnum.JIRA);
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
    public void test_get_instant_from_timezone_null() {
        assertThat(TrackerUtils.getInstantFromTimezone(null)).isNull();
    }

    @Test
    public void test_get_instant_from_bad_timezone() {
        assertThat(TrackerUtils.getInstantFromTimezone("BAD_TIMEZONE")).isNull();
    }
}
