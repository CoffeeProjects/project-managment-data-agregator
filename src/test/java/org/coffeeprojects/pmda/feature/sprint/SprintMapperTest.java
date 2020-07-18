package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class SprintMapperTest {

    private final SprintMapper sprintMapper = Mappers.getMapper(SprintMapper.class);

    @Test
    void to_entity_should_map_sprint_jira_bean_to_user_entity() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = simpleDateFormat.parse("2020-07-18 10:31:56");

        // Given
        SprintJiraBean sprintJiraBean = new SprintJiraBean()
                .setId("1")
                .setRapidViewId("1")
                .setState("State")
                .setName("Name")
                .setGoal("Goal")
                .setStartDate(date)
                .setEndDate(date)
                .setCompleteDate(date);

        // When
        SprintEntity sprintEntity = sprintMapper.toEntity(sprintJiraBean);

        // Then
        Instant instant = Instant.parse("2020-07-18T08:31:56.00Z");
        SprintEntity expectedSprintEntity = new SprintEntity()
                .setId(new CompositeIdBaseEntity().setClientId("1"))
                .setRapidViewId("1")
                .setState("State")
                .setName("Name")
                .setGoal("Goal")
                .setStartDate(instant)
                .setEndDate(instant)
                .setCompleteDate(instant);

        assertThat(sprintEntity).isEqualToComparingFieldByField(expectedSprintEntity);
    }
}
