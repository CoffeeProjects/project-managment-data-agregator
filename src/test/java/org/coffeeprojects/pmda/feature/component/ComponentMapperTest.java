package org.coffeeprojects.pmda.feature.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ComponentMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class ComponentMapperTest {

    @Autowired
    private ComponentMapper componentMapper;

    @Test
    public void to_entity_should_map_component_jira_bean_to_user_entity() {

        // Given
        ComponentJiraBean componentJiraBean = new ComponentJiraBean()
                .setId("1")
                .setName("Name");

        // When
        ComponentEntity componentEntity = componentMapper.toEntity(componentJiraBean);

        // Then
        ComponentEntity expectedComponentEntity = ((ComponentEntity) new ComponentEntity()
                .setId("1"))
                .setName("Name");
        assertThat(componentEntity).isEqualToComparingFieldByField(expectedComponentEntity);
    }
}
