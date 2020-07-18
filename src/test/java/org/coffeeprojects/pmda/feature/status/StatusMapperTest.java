package org.coffeeprojects.pmda.feature.status;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class StatusMapperTest {

    private final StatusMapper statusMapper = Mappers.getMapper(StatusMapper.class);

    @Test
    void to_entity_should_map_status_jira_bean_to_user_entity() {
        // Given
        StatusJiraBean statusJiraBean = new StatusJiraBean()
                .setId("1")
                .setName("KO")
                .setDescription("Nom KO");

        // When
        StatusEntity statusEntity = statusMapper.toEntity(statusJiraBean);

        // Then
        StatusEntity expectedStatusEntity = new StatusEntity()
                .setId(new CompositeIdBaseEntity().setClientId("1"))
                .setName("KO")
                .setDescription("Nom KO");

        assertThat(statusEntity).isEqualToComparingFieldByField(expectedStatusEntity);
    }
}
