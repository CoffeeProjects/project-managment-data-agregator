package org.coffeeprojects.pmda.feature.component;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}
