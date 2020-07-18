package org.coffeeprojects.pmda.feature.version;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class VersionMapperTest {

    private final VersionMapper versionMapper = Mappers.getMapper(VersionMapper.class);

    @Test
    void to_entity_should_map_version_jira_bean_to_user_entity() {
        // Given
        VersionJiraBean versionJiraBean = new VersionJiraBean()
                .setId("123")
                .setName("V1.1");

        // When
        VersionEntity versionEntity = versionMapper.toEntity(versionJiraBean);

        // Then
        VersionEntity expectedVersionEntity = new VersionEntity()
                .setId(new CompositeIdBaseEntity().setClientId("123"))
                .setName("V1.1");

        assertThat(versionEntity).isEqualToComparingFieldByField(expectedVersionEntity);
    }
}
