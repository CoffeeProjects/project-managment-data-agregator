package org.coffeeprojects.pmda.sprint;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SprintMapperImpl.class)
@RunWith(SpringRunner.class)
public class SprintMapperTest {

    @Autowired
    private SprintMapper sprintMapper;

    @Test
    public void to_entity_should_map_sprint_jira_bean_to_user_entity() {

        Date date = new Date();

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
        SprintEntity expectedSprintEntity = new SprintEntity()
                .setId("1")
                .setRapidViewId("1")
                .setState("State")
                .setName("Name")
                .setGoal("Goal")
                .setStartDate(date)
                .setEndDate(date)
                .setCompleteDate(date);
        assertThat(sprintEntity).isEqualToComparingFieldByField(expectedSprintEntity);
    }
}
