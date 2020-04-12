package org.coffeeprojects.pmda.feature.sprint;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private SprintUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void toEntity(List<String> input, IssueEntity output) {
        // Mapping des sprints
        if (input != null && !input.isEmpty()) {
            Set<SprintEntity> sprintEntities = new HashSet();
            input.stream()
                .filter(StringUtils::isNotEmpty)
                .forEach(p -> {
                    SprintEntity sprintEntity = new SprintEntity();
                    String id = StringUtils.substringAfter(p, ID_FIELD_NAME);
                    String rapidView = StringUtils.substringAfterLast(id, RAPID_VIEW_FIELD_NAME);
                    String state = StringUtils.substringAfterLast(rapidView, STATE_FIELD_NAME);
                    String name = StringUtils.substringAfterLast(state, NAME_FIELD_NAME);
                    String goal = StringUtils.substringAfterLast(name, GOAL_FIELD_NAME);
                    String startDate = StringUtils.substringAfterLast(goal, START_DATE_FIELD_NAME);
                    String endDate = StringUtils.substringAfterLast(startDate, END_DATE_FIELD_NAME);
                    String completeDate = StringUtils.substringAfterLast(endDate, COMPLETE_DATE_FIELD_NAME);

                    sprintEntity.setId(new CompositeIdBaseEntity().setClientId(StringUtils.replace(id, RAPID_VIEW_FIELD_NAME + rapidView, StringUtils.EMPTY)));
                    sprintEntity.setRapidViewId(StringUtils.replace(rapidView, STATE_FIELD_NAME + state, StringUtils.EMPTY));
                    sprintEntity.setState(StringUtils.replace(state, NAME_FIELD_NAME + name, StringUtils.EMPTY));
                    sprintEntity.setName(StringUtils.replace(name, GOAL_FIELD_NAME + goal, StringUtils.EMPTY));
                    sprintEntity.setGoal(StringUtils.replace(goal, START_DATE_FIELD_NAME + startDate, StringUtils.EMPTY));
                    sprintEntity.setStartDate(TrackerUtils.getInstantFromTimezone(StringUtils.replace(startDate, END_DATE_FIELD_NAME + endDate, StringUtils.EMPTY)));
                    sprintEntity.setEndDate(TrackerUtils.getInstantFromTimezone(StringUtils.replace(endDate, COMPLETE_DATE_FIELD_NAME + completeDate, StringUtils.EMPTY)));
                    sprintEntity.setCompleteDate(TrackerUtils.getInstantFromTimezone(StringUtils.substringAfterLast(completeDate, COMPLETE_DATE_FIELD_NAME)));

                    sprintEntities.add(sprintEntity);
                });
            output.setSprints(sprintEntities);
        } else {
            log.info("No sprint to fill for this issue : {}", output);
        }
    }
}
