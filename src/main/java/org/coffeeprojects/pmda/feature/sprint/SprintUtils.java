package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.feature.issue.IssueEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SprintUtils {
    public Set<IssueEntity> updateLastSprintsValuesFromIssueEntities(Set<IssueEntity> issueEntities) {
         if (issueEntities != null) {
             List<IssueEntity> issueEntitiesSortedByDate = issueEntities.stream()
                     .sorted(Comparator.comparing(IssueEntity::getUpdated).reversed())
                     .collect(Collectors.toList());

            issueEntitiesSortedByDate.stream()
                    .filter(entitySorted -> entitySorted.getSprints() != null)
                    .forEach(entitySorted -> {
                        entitySorted.getSprints().stream()
                                .filter(sprintSorted -> sprintSorted.getId() != null)
                                .filter(sprintSorted -> sprintSorted.getId().getTrackerType() != null)
                                .filter(sprintSorted -> sprintSorted.getId().getTrackerId() != null)
                                .filter(sprintSorted -> sprintSorted.getId().getStorageId() != null)
                                .forEach(sprintSorted -> {
                            issueEntities.stream()
                                    .filter(entity -> entity.getSprints() != null)
                                    .forEach(entity -> {
                                        entity.getSprints().stream()
                                            .filter(sprint -> sprint.getId() != null)
                                            .filter(sprint -> sprint.getId().getTrackerType() != null)
                                            .filter(sprint -> sprint.getId().getTrackerId() != null)
                                            .filter(sprint -> sprint.getId().getStorageId() != null)
                                            .filter(sprint -> sprint.getId().getTrackerType().equals(sprintSorted.getId().getTrackerType()))
                                            .filter(sprint -> sprint.getId().getTrackerId().equals(sprintSorted.getId().getTrackerId()))
                                            .forEach(sprint -> {
                                                sprint.setRapidViewId(sprintSorted.getRapidViewId());
                                                sprint.setState(sprintSorted.getState());
                                                sprint.setName(sprintSorted.getName());
                                                sprint.setGoal(sprintSorted.getGoal());
                                                sprint.setStartDate(sprintSorted.getStartDate());
                                                sprint.setStartDate(sprintSorted.getEndDate());
                                                sprint.setStartDate(sprintSorted.getCompleteDate());
                                            });
                                    });
                                });
                    });
         }
        return issueEntities;
    }
}
