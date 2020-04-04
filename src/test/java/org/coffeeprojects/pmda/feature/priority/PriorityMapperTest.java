package org.coffeeprojects.pmda.feature.priority;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PriorityMapperImpl.class)
@ExtendWith(SpringExtension.class)
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
        PriorityEntity expectedPriorityEntity = ((PriorityEntity) new PriorityEntity()
                .setId("1"))
                .setName("Name");
        assertThat(priorityEntity).isEqualToComparingFieldByField(expectedPriorityEntity);
    }
}
