package org.coffeeprojects.pmda.tracker;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.component.ComponentEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.issueType.IssueTypeEntity;
import org.coffeeprojects.pmda.feature.priority.PriorityEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEnum;
import org.coffeeprojects.pmda.feature.resolution.ResolutionEntity;
import org.coffeeprojects.pmda.feature.sprint.SprintEntity;
import org.coffeeprojects.pmda.feature.status.StatusEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.version.VersionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class TrackerUtilsTest {

    @Test
    public void test_fill_ids_from_issue_entity_field_by_field() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setStorageId("1").setTrackerId("1").setTrackerType(ProjectEnum.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setStorageId("1"));


        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getId().getTrackerId()).isEqualTo("1");

        // Assignee
        UserEntity assignee = new UserEntity();
        assignee.setId(new CompositeIdBaseEntity().setStorageId("1"));
        issueEntity.setAssignee(assignee);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getAssignee().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getAssignee().getId().getTrackerId()).isEqualTo("1");

        // Creator
        UserEntity creator = new UserEntity();
        creator.setId(new CompositeIdBaseEntity().setStorageId("1"));
        issueEntity.setCreator(creator);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getCreator().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getCreator().getId().getTrackerId()).isEqualTo("1");

        // Reporter
        UserEntity reporter = new UserEntity();
        reporter.setId(new CompositeIdBaseEntity().setStorageId("1"));
        issueEntity.setReporter(reporter);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getReporter().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getReporter().getId().getTrackerId()).isEqualTo("1");

        // Status
        StatusEntity status = new StatusEntity();
        status.setId(new CompositeIdBaseEntity().setStorageId("1"));
        issueEntity.setStatus(status);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getStatus().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getStatus().getId().getTrackerId()).isEqualTo("1");

        // Resolution
        ResolutionEntity resolution = new ResolutionEntity();
        resolution.setId(new CompositeIdBaseEntity().setStorageId("1"));
        issueEntity.setResolution(resolution);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getResolution().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getResolution().getId().getTrackerId()).isEqualTo("1");

        // Priority
        PriorityEntity priority = new PriorityEntity();
        priority.setId(new CompositeIdBaseEntity().setStorageId("1"));
        issueEntity.setPriority(priority);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getPriority().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getPriority().getId().getTrackerId()).isEqualTo("1");

        // Priority
        IssueTypeEntity issueType = new IssueTypeEntity();
        issueType.setId(new CompositeIdBaseEntity().setStorageId("1"));
        issueEntity.setIssueType(issueType);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getIssueType().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getIssueType().getId().getTrackerId()).isEqualTo("1");

        // Priority
        issueEntity.setProject(projectEntity);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getProject().getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getProject().getId().getTrackerId()).isEqualTo("1");

        // Fix versions
        Set<VersionEntity> fixVersions = new HashSet();
        VersionEntity fixVersion1 = new VersionEntity();
        fixVersion1.setId(new CompositeIdBaseEntity().setStorageId("1"));
        VersionEntity fixVersion2 = new VersionEntity();
        fixVersion2.setId(new CompositeIdBaseEntity().setStorageId("1"));
        fixVersions.add(fixVersion1);
        fixVersions.add(fixVersion2);
        issueEntity.setFixVersions(fixVersions);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getFixVersions().stream()
                .forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
                    assertThat(p.getId().getTrackerId()).isEqualTo("1");
                });

        // Components
        Set<ComponentEntity> components = new HashSet();
        ComponentEntity component1 = new ComponentEntity();
        component1.setId(new CompositeIdBaseEntity().setStorageId("1"));
        ComponentEntity component2 = new ComponentEntity();
        component2.setId(new CompositeIdBaseEntity().setStorageId("1"));
        components.add(component1);
        components.add(component2);
        issueEntity.setComponents(components);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getComponents().stream()
                .forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
                    assertThat(p.getId().getTrackerId()).isEqualTo("1");
                });

        // Sprints
        Set<SprintEntity> sprints = new HashSet();
        SprintEntity sprint1 = new SprintEntity();
        sprint1.setId(new CompositeIdBaseEntity().setStorageId("1"));
        SprintEntity sprint2 = new SprintEntity();
        sprint2.setId(new CompositeIdBaseEntity().setStorageId("1"));
        sprints.add(sprint1);
        sprints.add(sprint2);
        issueEntity.setSprints(sprints);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        issueEntity.getSprints().stream()
                .forEach(p -> {
                    assertThat(p.getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
                    assertThat(p.getId().getTrackerId()).isEqualTo("1");
                });
    }

    @Test
    public void test_fill_ids_from_issue_entity() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setStorageId("1").setTrackerId("1").setTrackerType(ProjectEnum.JIRA));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setStorageId("1"));

        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isEqualTo(ProjectEnum.JIRA);
        assertThat(issueEntity.getId().getTrackerId()).isEqualTo("1");
    }

    @Test
    public void test_fill_ids_from_issue_entity_without_tracker_id_or_tracker_type() {
        // ProjectEntity
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setStorageId("1"));
        // IssueEntity
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(new CompositeIdBaseEntity().setStorageId("1"));

        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isNull();
        assertThat(issueEntity.getId().getTrackerId()).isNull();

        projectEntity.getId().setTrackerType(ProjectEnum.JIRA);
        projectEntity.getId().setTrackerId(null);
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isNull();
        assertThat(issueEntity.getId().getTrackerId()).isNull();

        projectEntity.getId().setTrackerType(null);
        projectEntity.getId().setTrackerId("1");
        TrackerUtils.fillIdsFromIssueEntity(projectEntity, issueEntity);
        assertThat(issueEntity.getId().getTrackerType()).isNull();
        assertThat(issueEntity.getId().getTrackerId()).isNull();
    }

    @Test
    public void test_get_date_from_timezone_null() {
        assertThat(TrackerUtils.getDateFromTimezone(null)).isNull();
    }

    @Test
    public void test_get_date_from_bad_timezone() {
        assertThat(TrackerUtils.getDateFromTimezone("BAD_TIMEZONE")).isNull();
    }
}
