package org.coffeeprojects.pmda.feature.sprint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SprintMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class SprintMapperTest {

    @Autowired
    private SprintMapper sprintMapper;

    @Test
    public void to_entity_should_map_sprint_jira_bean_to_user_entity() {

        Date date = new Date();

        // Given
        SprintJiraBean sprintJiraBean = new SprintJiraBean()
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
