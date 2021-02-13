package org.coffeeprojects.pmda.feature.sprint;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SprintUtils {

    private static final Logger log = LoggerFactory.getLogger(SprintUtils.class);

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

    public static void toEntity(List<LinkedHashMap<Object, Object>> input, IssueEntity output) {
        // Mapping des sprints
        if (input != null && !input.isEmpty()) {
            Set<SprintEntity> sprintEntities = new HashSet<>();
            input.stream()
                .forEach(p -> {
                    SprintEntity sprintEntity = new SprintEntity();

                    String id = p.get("id") != null ? p.get("id").toString() : StringUtils.EMPTY;
                    String name = p.get("name") != null ? p.get("name").toString() : StringUtils.EMPTY;
                    String state = p.get("state") != null ? p.get("state").toString() : StringUtils.EMPTY;
                    String rapidViewId = p.get("boardId") != null ? p.get("boardId").toString() : StringUtils.EMPTY;
                    String goal = p.get("goal") != null ? p.get("goal").toString() : StringUtils.EMPTY;
                    String startDate = p.get("startDate") != null ? p.get("startDate").toString() : StringUtils.EMPTY;
                    String endDate = p.get("endDate") != null ? p.get("endDate").toString() : StringUtils.EMPTY;
                    String completeDate = p.get("completeDate") != null ? p.get("completeDate").toString() : StringUtils.EMPTY;

                    sprintEntity.setId(new CompositeIdBaseEntity().setClientId(id));
                    sprintEntity.setName(name);
                    sprintEntity.setState(state);
                    sprintEntity.setRapidViewId(rapidViewId);
                    sprintEntity.setGoal(goal);
                    sprintEntity.setStartDate(TrackerUtils.getInstantFromTimezone(startDate));
                    sprintEntity.setEndDate(TrackerUtils.getInstantFromTimezone(endDate));
                    sprintEntity.setCompleteDate(TrackerUtils.getInstantFromTimezone(completeDate));

                    sprintEntities.add(sprintEntity);
                });
            output.setSprints(sprintEntities);
        } else {
            log.info("No sprint to fill for this issue : {}", output);
        }
    }
}
