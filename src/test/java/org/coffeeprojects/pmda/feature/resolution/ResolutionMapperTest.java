package org.coffeeprojects.pmda.feature.resolution;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class ResolutionMapperTest {

    private final ResolutionMapper resolutionMapper = Mappers.getMapper(ResolutionMapper.class);

    @Test
    void to_entity_should_map_resolution_jira_bean_to_user_entity() {
        // Given
        ResolutionJiraBean resolutionJiraBean = new ResolutionJiraBean()
                .setId("id")
                .setName("KO")
                .setDescription("Nom KO");

        // When
        ResolutionEntity resolutionEntity = resolutionMapper.toEntity(resolutionJiraBean);

        // Then
        ResolutionEntity expectedResolutionEntity = new ResolutionEntity()
                .setId(new CompositeIdBaseEntity().setClientId("id"))
                .setName("KO")
                .setDescription("Nom KO");

        assertThat(resolutionEntity).usingRecursiveComparison().isEqualTo(expectedResolutionEntity);
    }

    @Test
    void to_entity_with_version_jira_bean_null() {
        // Given / When / Then
        assertThat(resolutionMapper.toEntity(null)).isNull();
    }
}
