package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SprintUtilsTest {

    @Disabled
    @Test
    void test_update_last_sprints_values_from_issue_entities_with_sprints() {
        // 1er Issue Entity
        CompositeIdBaseEntity sprintId1_1 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("1").setClientId("1");
        SprintEntity sprint1_1 = new SprintEntity();
        sprint1_1.setId(sprintId1_1);
        sprint1_1.setRapidViewId("1_1");
        sprint1_1.setName("Sprint_name_1_1");
        sprint1_1.setGoal("Sprint_goal_1_1");
        sprint1_1.setStartDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_1.setEndDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_1.setCompleteDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 10, 0, 0)).getTime()).toInstant());
        CompositeIdBaseEntity sprintId1_2 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("1").setClientId("2");
        SprintEntity sprint1_2 = new SprintEntity();
        sprint1_2.setId(sprintId1_2);
        sprint1_2.setRapidViewId("1_2");
        sprint1_2.setName("Sprint_name_1_2");
        sprint1_2.setGoal("Sprint_goal_1_2");
        sprint1_2.setStartDate(((new GregorianCalendar(2020, Calendar.MARCH, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_2.setEndDate(((new GregorianCalendar(2020, Calendar.MARCH, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_2.setCompleteDate(((new GregorianCalendar(2020, Calendar.MARCH, 1, 10, 0, 0)).getTime()).toInstant());
        Set<SprintEntity> sprints1 = new HashSet<>();
        sprints1.add(sprint1_1);
        sprints1.add(sprint1_2);
        IssueEntity issue1 = new IssueEntity();
        issue1.setUpdated((new GregorianCalendar(2020, Calendar.MARCH, 9, 10, 0, 0)).getTime());
        issue1.setSprints(sprints1);

        // 2e Issue Entity
        CompositeIdBaseEntity sprintId2_1 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("1").setClientId("1");
        SprintEntity sprint2_1 = new SprintEntity();
        sprint2_1.setId(sprintId2_1);
        sprint2_1.setRapidViewId("2_1");
        sprint2_1.setName("Sprint_name_2_1");
        sprint2_1.setGoal("Sprint_goal_2_1");
        sprint2_1.setStartDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_1.setEndDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_1.setCompleteDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 2, 10, 0, 0)).getTime()).toInstant());
        CompositeIdBaseEntity sprintId2_2 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("1").setClientId("2");
        SprintEntity sprint2_2 = new SprintEntity();
        sprint2_2.setId(sprintId2_2);
        sprint2_2.setRapidViewId("2_2");
        sprint2_2.setName("Sprint_name_2_2");
        sprint2_2.setGoal("Sprint_goal_2_2");
        sprint2_2.setStartDate(((new GregorianCalendar(2020, Calendar.MARCH, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_2.setEndDate(((new GregorianCalendar(2020, Calendar.MARCH, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_2.setCompleteDate(((new GregorianCalendar(2020, Calendar.MARCH, 2, 10, 0, 0)).getTime()).toInstant());
        Set<SprintEntity> sprints2 = new HashSet<>();
        sprints2.add(sprint2_1);
        sprints2.add(sprint2_2);
        IssueEntity issue2 = new IssueEntity();
        issue2.setUpdated((new GregorianCalendar(2020, Calendar.MARCH, 10, 10, 0, 0)).getTime());
        issue2.setSprints(sprints2);

        Set<IssueEntity> issueEntities = new HashSet<>();
        issueEntities.add(issue1);
        issueEntities.add(issue2);

        SprintUtils.updateLastSprintsValuesFromIssueEntities(new ArrayList<>(issueEntities));
        assertThat(sprint1_1.getRapidViewId()).isEqualTo(sprint2_1.getRapidViewId());
        assertThat(sprint1_1.getName()).isEqualTo(sprint2_1.getName());
        assertThat(sprint1_1.getGoal()).isEqualTo(sprint2_1.getGoal());
        assertThat(sprint1_1.getStartDate()).isEqualTo(sprint2_1.getStartDate());
        assertThat(sprint1_1.getEndDate()).isEqualTo(sprint2_1.getEndDate());
        assertThat(sprint1_1.getCompleteDate()).isEqualTo(sprint2_1.getCompleteDate());

        assertThat(sprint1_2.getRapidViewId()).isEqualTo(sprint2_2.getRapidViewId());
        assertThat(sprint1_2.getName()).isEqualTo(sprint2_2.getName());
        assertThat(sprint1_2.getGoal()).isEqualTo(sprint2_2.getGoal());
        assertThat(sprint1_2.getStartDate()).isEqualTo(sprint2_2.getStartDate());
        assertThat(sprint1_2.getEndDate()).isEqualTo(sprint2_2.getEndDate());
        assertThat(sprint1_2.getCompleteDate()).isEqualTo(sprint2_2.getCompleteDate());
    }

    @Test
    void test_update_last_sprints_values_from_issue_entities_with_sprints_different_trackers_id() {
        // 1er Issue Entity
        CompositeIdBaseEntity sprintId1_1 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("1").setClientId("1");
        SprintEntity sprint1_1 = new SprintEntity();
        sprint1_1.setId(sprintId1_1);
        sprint1_1.setRapidViewId("1_1");
        sprint1_1.setName("Sprint_name_1_1");
        sprint1_1.setGoal("Sprint_goal_1_1");
        sprint1_1.setStartDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_1.setEndDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_1.setCompleteDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 10, 0, 0)).getTime()).toInstant());
        CompositeIdBaseEntity sprintId1_2 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("2").setClientId("2");
        SprintEntity sprint1_2 = new SprintEntity();
        sprint1_2.setId(sprintId1_2);
        sprint1_2.setRapidViewId("1_2");
        sprint1_2.setName("Sprint_name_1_2");
        sprint1_2.setGoal("Sprint_goal_1_2");
        sprint1_2.setStartDate(((new GregorianCalendar(2020, Calendar.MARCH, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_2.setEndDate(((new GregorianCalendar(2020, Calendar.MARCH, 1, 10, 0, 0)).getTime()).toInstant());
        sprint1_2.setCompleteDate(((new GregorianCalendar(2020, Calendar.MARCH, 1, 10, 0, 0)).getTime()).toInstant());
        Set<SprintEntity> sprints1 = new HashSet<>();
        sprints1.add(sprint1_1);
        sprints1.add(sprint1_2);
        IssueEntity issue1 = new IssueEntity();
        issue1.setUpdated((new GregorianCalendar(2020, Calendar.MARCH, 9, 10, 0, 0)).getTime());
        issue1.setSprints(sprints1);

        // 2e Issue Entity
        CompositeIdBaseEntity sprintId2_1 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("3").setClientId("1");
        SprintEntity sprint2_1 = new SprintEntity();
        sprint2_1.setId(sprintId2_1);
        sprint2_1.setRapidViewId("2_1");
        sprint2_1.setName("Sprint_name_2_1");
        sprint2_1.setGoal("Sprint_goal_2_1");
        sprint2_1.setStartDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_1.setEndDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_1.setCompleteDate(((new GregorianCalendar(2020, Calendar.FEBRUARY, 2, 10, 0, 0)).getTime()).toInstant());
        CompositeIdBaseEntity sprintId2_2 = new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("4").setClientId("2");
        SprintEntity sprint2_2 = new SprintEntity();
        sprint2_2.setId(sprintId2_2);
        sprint2_2.setRapidViewId("2_2");
        sprint2_2.setName("Sprint_name_2_2");
        sprint2_2.setGoal("Sprint_goal_2_2");
        sprint2_2.setStartDate(((new GregorianCalendar(2020, Calendar.MARCH, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_2.setEndDate(((new GregorianCalendar(2020, Calendar.MARCH, 2, 10, 0, 0)).getTime()).toInstant());
        sprint2_2.setCompleteDate(((new GregorianCalendar(2020, Calendar.MARCH, 2, 10, 0, 0)).getTime()).toInstant());
        Set<SprintEntity> sprints2 = new HashSet<>();
        sprints2.add(sprint2_1);
        sprints2.add(sprint2_2);
        IssueEntity issue2 = new IssueEntity();
        issue2.setUpdated((new GregorianCalendar(2020, Calendar.MARCH, 10, 10, 0, 0)).getTime());
        issue2.setSprints(sprints2);

        Set<IssueEntity> issueEntities = new HashSet<>();
        issueEntities.add(issue1);
        issueEntities.add(issue2);

        SprintUtils.updateLastSprintsValuesFromIssueEntities(new ArrayList<>(issueEntities));
        assertThat(sprint1_1.getRapidViewId()).isNotEqualTo(sprint2_1.getRapidViewId());
        assertThat(sprint1_1.getName()).isNotEqualTo(sprint2_1.getName());
        assertThat(sprint1_1.getGoal()).isNotEqualTo(sprint2_1.getGoal());
        assertThat(sprint1_1.getStartDate()).isNotEqualTo(sprint2_1.getStartDate());
        assertThat(sprint1_1.getEndDate()).isNotEqualTo(sprint2_1.getEndDate());
        assertThat(sprint1_1.getCompleteDate()).isNotEqualTo(sprint2_1.getCompleteDate());

        assertThat(sprint1_2.getRapidViewId()).isNotEqualTo(sprint2_2.getRapidViewId());
        assertThat(sprint1_2.getName()).isNotEqualTo(sprint2_2.getName());
        assertThat(sprint1_2.getGoal()).isNotEqualTo(sprint2_2.getGoal());
        assertThat(sprint1_2.getStartDate()).isNotEqualTo(sprint2_2.getStartDate());
        assertThat(sprint1_2.getEndDate()).isNotEqualTo(sprint2_2.getEndDate());
        assertThat(sprint1_2.getCompleteDate()).isNotEqualTo(sprint2_2.getCompleteDate());
    }

    @Test
    void test_update_last_sprints_values_from_issue_entities_with_empty_sprints() {
        // 1er Issue Entity
        SprintEntity sprint1_1 = new SprintEntity();
        SprintEntity sprint1_2 = new SprintEntity();
        Set<SprintEntity> sprints1 = new HashSet<>();
        sprints1.add(sprint1_1);
        sprints1.add(sprint1_2);
        IssueEntity issue1 = new IssueEntity();
        issue1.setSprints(sprints1);

        // 2e Issue Entity
        SprintEntity sprint2_1 = new SprintEntity();
        SprintEntity sprint2_2 = new SprintEntity();
        Set<SprintEntity> sprints2 = new HashSet<>();
        sprints1.add(sprint2_1);
        sprints1.add(sprint2_2);
        IssueEntity issue2 = new IssueEntity();
        issue2.setSprints(sprints2);

        Set<IssueEntity> issueEntities = new HashSet<>();
        issueEntities.add(issue1);
        issueEntities.add(issue2);

        SprintUtils.updateLastSprintsValuesFromIssueEntities(new ArrayList<>(issueEntities));
        assertThat(sprint1_1.getRapidViewId()).isNull();
        assertThat(sprint1_1.getName()).isNull();
        assertThat(sprint1_1.getGoal()).isNull();
        assertThat(sprint1_1.getStartDate()).isNull();
        assertThat(sprint1_1.getEndDate()).isNull();
        assertThat(sprint1_1.getCompleteDate()).isNull();

        assertThat(sprint1_2.getRapidViewId()).isNull();
        assertThat(sprint1_2.getName()).isNull();
        assertThat(sprint1_2.getGoal()).isNull();
        assertThat(sprint1_2.getStartDate()).isNull();
        assertThat(sprint1_2.getEndDate()).isNull();
        assertThat(sprint1_2.getCompleteDate()).isNull();
    }

    @Test
    void test_update_last_sprints_values_from_issue_entities_with_empty_entities() {
        IssueEntity issue1 = new IssueEntity();
        IssueEntity issue2 = new IssueEntity();
        Set<IssueEntity> issueEntities = new HashSet<>();
        issueEntities.add(issue1);
        issueEntities.add(issue2);

        SprintUtils.updateLastSprintsValuesFromIssueEntities(new ArrayList<>(issueEntities));
        assertThat(issue1.getSprints()).isNull();
        assertThat(issue2.getSprints()).isNull();
    }

    @Test
    void test_update_last_sprints_values_from_issue_entities_with_empty_list() {
        Set<IssueEntity> issueEntities = new HashSet<>();

        SprintUtils.updateLastSprintsValuesFromIssueEntities(new ArrayList<>(issueEntities));
        assertThat(issueEntities).isEmpty();
    }

    @Test
    void test_update_last_sprints_values_from_issue_entities_null() {
        assertThat(SprintUtils.updateLastSprintsValuesFromIssueEntities(null)).isNull();
    }

    @Test
    void test_get_sprints_by_issue_jira_bean_with_sprints_null() {
        IssueEntity issueEntity = new IssueEntity();
        SprintUtils.toEntity(null, issueEntity);

        // Then
        assertThat(issueEntity.getSprints()).isNull();
    }

    @Test
    void test_get_sprints_by_issue_jira_bean_with_sprints() {
        LinkedHashMap<Object, Object> sprint = new LinkedHashMap<>();
        sprint.put("id", "2");
        sprint.put("boardId", "1");
        sprint.put("state", "FUTURE");
        sprint.put("name", "PMDA ,goal=2 (%+\"'-$*€/\\|)");
        sprint.put("goal", "FPEfzefoç !!çà) ù%% ==+\nLoL \"'-$*€  ,\n/   \\ | Test( coucou");
        sprint.put("sequence", "2");
        List<LinkedHashMap<Object, Object>> sprints = new ArrayList<>();
        sprints.add(sprint);

        SprintEntity expectedSprintEntity = new SprintEntity();

        IssueEntity issueEntity = new IssueEntity();
        SprintUtils.toEntity(sprints, issueEntity);

        // Then
        issueEntity.getSprints()
                .forEach(p -> {
                    assertThat(p.getId().getClientId().equals("2"));
                    assertThat(p.getRapidViewId()).isEqualTo("1");
                    assertThat(p.getState()).isEqualTo("FUTURE");
                    assertThat(p.getName()).isEqualTo("PMDA ,goal=2 (%+\"'-$*€/\\|)");
                    assertThat(p.getGoal()).isEqualTo("FPEfzefoç !!çà) ù%% ==+\nLoL \"'-$*€  ,\n/   \\ | Test( coucou");
                });

    }
}
