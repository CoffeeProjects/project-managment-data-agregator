package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.feature.issue.IssueEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SprintUtils {
    public static Set<IssueEntity> updateLastSprintsValuesFromIssueEntities(Set<IssueEntity> issueEntities) {
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

    private static void browseSprintsToUpdateFromSortedIssueEntities(Set<IssueEntity> issueEntities, List<IssueEntity> issueEntitiesSortedByModifiedDate) {
        issueEntitiesSortedByModifiedDate.stream()
                .filter(entitySorted -> entitySorted.getSprints() != null)
                .forEach(entitySorted -> entitySorted.getSprints().stream()
                        .filter(sprintSorted -> sprintSorted.getId() != null)
                        .filter(sprintSorted -> sprintSorted.getId().getTrackerType() != null)
                        .filter(sprintSorted -> sprintSorted.getId().getTrackerId() != null)
                        .filter(sprintSorted -> sprintSorted.getId().getStorageId() != null)
                        .forEach(sprintSorted -> browseSprintsToUpdateFromIssueEntities(issueEntities, sprintSorted)));
    }

    private static void browseSprintsToUpdateFromIssueEntities(Set<IssueEntity> issueEntities, SprintEntity sortedSprint) {
        issueEntities.stream()
                .filter(entity -> entity.getSprints() != null)
                .forEach(entity -> entity.getSprints().stream()
                    .filter(sprint -> sprint.getId() != null)
                    .filter(sprint -> sprint.getId().getTrackerType() != null)
                    .filter(sprint -> sprint.getId().getTrackerId() != null)
                    .filter(sprint -> sprint.getId().getStorageId() != null)
                    .filter(sprint -> sprint.getId().getTrackerType().equals(sortedSprint.getId().getTrackerType()))
                    .filter(sprint -> sprint.getId().getTrackerId().equals(sortedSprint.getId().getTrackerId()))
                    .filter(sprint -> sprint.getId().getStorageId().equals(sortedSprint.getId().getStorageId()))
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
}
