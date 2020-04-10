package org.coffeeprojects.pmda.feature.sprint;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.tracker.TrackerUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SprintUtils {
    public static void toEntity(List<String> input, IssueEntity output) {
        // Mapping des sprints
        Set<SprintEntity> sprintEntities = new HashSet();
        if (input != null) {
            input.stream()
                    .forEach(p -> {
                        SprintEntity sprintEntity = new SprintEntity();
                        String id = StringUtils.substringAfter(p, "id=");
                        String rapidView = StringUtils.substringAfterLast(id, ",rapidViewId=");
                        String state = StringUtils.substringAfterLast(rapidView, ",state=");
                        String name = StringUtils.substringAfterLast(state, ",name=");
                        String goal = StringUtils.substringAfterLast(name, ",goal=");
                        String startDate = StringUtils.substringAfterLast(goal, ",startDate=");
                        String endDate = StringUtils.substringAfterLast(startDate, ",endDate=");
                        String completeDate = StringUtils.substringAfterLast(endDate, ",completeDate=");

                        sprintEntity.setId(new CompositeIdBaseEntity().setClientId(StringUtils.replace(id, ",rapidViewId=" + rapidView, StringUtils.EMPTY)));
                        sprintEntity.setRapidViewId(StringUtils.replace(rapidView, ",state=" + state, StringUtils.EMPTY));
                        sprintEntity.setState(StringUtils.replace(state, ",name=" + name, StringUtils.EMPTY));
                        sprintEntity.setName(StringUtils.replace(name, ",goal=" + goal, StringUtils.EMPTY));
                        sprintEntity.setGoal(StringUtils.replace(goal, ",startDate=" + startDate, StringUtils.EMPTY));
                        sprintEntity.setStartDate(TrackerUtils.getInstantFromTimezone(StringUtils.replace(startDate, ",endDate=" + endDate, StringUtils.EMPTY)));
                        sprintEntity.setEndDate(TrackerUtils.getInstantFromTimezone(StringUtils.replace(endDate, ",completeDate=" + completeDate, StringUtils.EMPTY)));
                        sprintEntity.setCompleteDate(TrackerUtils.getInstantFromTimezone(StringUtils.substringAfterLast(completeDate, ",completeDate=")));

                        sprintEntities.add(sprintEntity);
                    });
            output.setSprints(sprintEntities);
        }
    }
}
