package org.coffeeprojects.pmda.feature.sprint;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SprintUtils {

    private static final Logger log = LoggerFactory.getLogger(SprintUtils.class);

    private static final String ID_FIELD_NAME = "id=";
    private static final String RAPID_VIEW_FIELD_NAME = ",rapidViewId=";
    private static final String STATE_FIELD_NAME = ",state=";
    private static final String NAME_FIELD_NAME = ",name=";
    private static final String GOAL_FIELD_NAME = ",goal=";
    private static final String START_DATE_FIELD_NAME = ",startDate=";
    private static final String END_DATE_FIELD_NAME = ",endDate=";
    private static final String COMPLETE_DATE_FIELD_NAME = ",completeDate=";
    private static final String COMMA = ",";
    private static final String BRACKET = "]";

    private SprintUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<IssueEntity> updateLastSprintsValuesFromIssueEntities(List<IssueEntity> issueEntities) {
        if (issueEntities != null) {
            List<IssueEntity> issueEntitiesSortedByDate = issueEntities.stream()
                    .filter(issueEntity -> issueEntity.getUpdated() != null)
                    .filter(issueEntity -> issueEntity.getSprints() != null)
                    .sorted(Comparator.comparing(IssueEntity::getUpdated).reversed())
                    .collect(Collectors.toList());

            browseSprintsToUpdateFromSortedIssueEntities(issueEntities, issueEntitiesSortedByDate);
        }
        return issueEntities;
    }

    private static void browseSprintsToUpdateFromSortedIssueEntities(List<IssueEntity> issueEntities, List<IssueEntity> issueEntitiesSortedByModifiedDate) {
        issueEntitiesSortedByModifiedDate.stream()
                .filter(entitySorted -> entitySorted.getSprints() != null)
                .forEach(entitySorted -> entitySorted.getSprints().stream()
                        .filter(sprintSorted -> sprintSorted.getId() != null)
                        .filter(sprintSorted -> sprintSorted.getId().getTrackerType() != null)
                        .filter(sprintSorted -> sprintSorted.getId().getTrackerLocalId() != null)
                        .filter(sprintSorted -> sprintSorted.getId().getClientId() != null)
                        .forEach(sprintSorted -> browseSprintsToUpdateFromIssueEntities(issueEntities, sprintSorted)));
    }

    private static void browseSprintsToUpdateFromIssueEntities(List<IssueEntity> issueEntities, SprintEntity sortedSprint) {
        issueEntities.stream()
                .filter(entity -> entity.getSprints() != null)
                .forEach(entity -> entity.getSprints().stream()
                        .filter(sprint -> sprint.getId() != null)
                        .filter(sprint -> sprint.getId().getTrackerType() != null)
                        .filter(sprint -> sprint.getId().getTrackerLocalId() != null)
                        .filter(sprint -> sprint.getId().getClientId() != null)
                        .filter(sprint -> sprint.getId().getTrackerType().equals(sortedSprint.getId().getTrackerType()))
                        .filter(sprint -> sprint.getId().getTrackerLocalId().equals(sortedSprint.getId().getTrackerLocalId()))
                        .filter(sprint -> sprint.getId().getClientId().equals(sortedSprint.getId().getClientId()))
                        .forEach(sprint -> fillSprintValues(sortedSprint, sprint)));
    }

    private static void fillSprintValues(SprintEntity sprintSorted, SprintEntity sprint) {
        sprint.setRapidViewId(sprintSorted.getRapidViewId());
        sprint.setState(sprintSorted.getState());
        sprint.setName(sprintSorted.getName());
        sprint.setGoal(sprintSorted.getGoal());
        sprint.setStartDate(sprintSorted.getStartDate());
        sprint.setEndDate(sprintSorted.getEndDate());
        sprint.setCompleteDate(sprintSorted.getCompleteDate());
    }

    public static void toEntity(List<String> input, IssueEntity output) {
        // Mapping des sprints
        if (input != null && !input.isEmpty()) {
            Set<SprintEntity> sprintEntities = new HashSet<>();
            input.stream()
                .filter(StringUtils::isNotEmpty)
                .forEach(p -> {
                    SprintEntity sprintEntity = new SprintEntity();
                    String id = StringUtils.substringAfter(p, ID_FIELD_NAME);
                    String rapidView = StringUtils.substringAfterLast(p, RAPID_VIEW_FIELD_NAME);
                    String state = StringUtils.substringAfterLast(p, STATE_FIELD_NAME);
                    String name = StringUtils.substringAfterLast(p, NAME_FIELD_NAME);
                    String goal = StringUtils.substringAfterLast(p, GOAL_FIELD_NAME);
                    String startDate = StringUtils.substringAfterLast(p, START_DATE_FIELD_NAME);
                    String endDate = StringUtils.substringAfterLast(p, END_DATE_FIELD_NAME);
                    String completeDate = StringUtils.substringAfterLast(p, COMPLETE_DATE_FIELD_NAME);

                    sprintEntity.setId(new CompositeIdBaseEntity().setClientId(StringUtils.substringBefore(StringUtils.substringBefore(id, COMMA), BRACKET)));
                    sprintEntity.setRapidViewId(StringUtils.substringBefore(StringUtils.substringBefore(rapidView, COMMA), BRACKET));
                    sprintEntity.setState(StringUtils.substringBefore(StringUtils.substringBefore(state, COMMA), BRACKET));
                    sprintEntity.setName(getFilteredTextField(name));
                    sprintEntity.setGoal(getFilteredTextField(goal));
                    sprintEntity.setStartDate(TrackerUtils.getInstantFromTimezone(StringUtils.substringBefore(StringUtils.substringBefore(startDate, COMMA), BRACKET)));
                    sprintEntity.setEndDate(TrackerUtils.getInstantFromTimezone(StringUtils.substringBefore(StringUtils.substringBefore(endDate, COMMA), BRACKET)));
                    sprintEntity.setCompleteDate(TrackerUtils.getInstantFromTimezone(StringUtils.substringBefore(StringUtils.substringBefore(completeDate, COMMA), BRACKET)));

                    sprintEntities.add(sprintEntity);
                });
            output.setSprints(sprintEntities);
        } else {
            log.info("No sprint to fill for this issue : {}", output);
        }
    }

    private static String getFilteredTextField(String value) {
        String filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(value, COMMA + ID_FIELD_NAME), BRACKET);
        filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(filteredValue, RAPID_VIEW_FIELD_NAME), BRACKET);
        filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(filteredValue, STATE_FIELD_NAME), BRACKET);
        filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(filteredValue, NAME_FIELD_NAME), BRACKET);
        filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(filteredValue, GOAL_FIELD_NAME), BRACKET);
        filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(filteredValue, START_DATE_FIELD_NAME), BRACKET);
        filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(filteredValue, END_DATE_FIELD_NAME), BRACKET);
        filteredValue = StringUtils.substringBefore(StringUtils.substringBeforeLast(filteredValue, COMPLETE_DATE_FIELD_NAME), BRACKET);

        return filteredValue;
    }
}
