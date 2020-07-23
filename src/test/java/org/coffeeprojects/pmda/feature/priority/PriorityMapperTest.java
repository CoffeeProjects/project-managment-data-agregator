package org.coffeeprojects.pmda.feature.priority;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class PriorityMapperTest {

    private final PriorityMapper priorityMapper = Mappers.getMapper(PriorityMapper.class);

    @Test
    void to_entity_should_map_priority_jira_bean_to_user_entity() {

        // Given
        PriorityJiraBean priorityJiraBean = new PriorityJiraBean()
                .setId("id")
                .setName("Name");

        // When
        PriorityEntity priorityEntity = priorityMapper.toEntity(priorityJiraBean);

        // Then
        PriorityEntity expectedPriorityEntity = new PriorityEntity()
                .setId(new CompositeIdBaseEntity().setClientId("id"))
                .setName("Name");

        assertThat(priorityEntity).isEqualToComparingFieldByField(expectedPriorityEntity);
    }
}
