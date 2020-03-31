package org.coffeeprojects.pmda.priority;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PriorityMapperImpl.class)
@RunWith(SpringRunner.class)
public class PriorityMapperTest {

    @Autowired
    private PriorityMapper priorityMapper;

    @Test
    public void to_entity_should_map_priority_jira_bean_to_user_entity() {

        // Given
        PriorityJiraBean priorityJiraBean = new PriorityJiraBean()
                .setId("1")
                .setName("Name");

        // When
        PriorityEntity priorityEntity = priorityMapper.toEntity(priorityJiraBean);

        // Then
        PriorityEntity expectedPriorityEntity = new PriorityEntity()
                .setId("1")
                .setName("Name");
        assertThat(priorityEntity).isEqualToComparingFieldByField(expectedPriorityEntity);
    }
}
