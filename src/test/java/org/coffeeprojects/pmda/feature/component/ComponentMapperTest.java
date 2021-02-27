package org.coffeeprojects.pmda.feature.component;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class ComponentMapperTest {

    private ComponentMapper componentMapper = Mappers.getMapper(ComponentMapper.class);

    @Test
    void to_entity_should_map_component_jira_bean_to_user_entity() {
        // Given
        ComponentJiraBean componentJiraBean = new ComponentJiraBean()
                .setId("id")
                .setName("Name");

        // When
        ComponentEntity componentEntity = componentMapper.toEntity(componentJiraBean);

        // Then
        ComponentEntity expectedComponentEntity = new ComponentEntity()
                .setId(new CompositeIdBaseEntity().setClientId("id"))
                .setName("Name");

        assertThat(componentEntity).isEqualToIgnoringGivenFields(expectedComponentEntity);
    }

    @Test
    void to_entity_with_version_jira_bean_null() {
        // Given / When / Then
        assertThat(componentMapper.toEntity(null)).isNull();
    }
}
