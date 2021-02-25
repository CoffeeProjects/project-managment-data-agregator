package org.coffeeprojects.pmda.feature.version;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class VersionMapperTest {

    private final VersionMapper versionMapper = Mappers.getMapper(VersionMapper.class);

    @Test
    void to_entity_should_map_version_jira_bean() {
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

        assertThat(versionEntity).usingRecursiveComparison().isEqualTo(expectedVersionEntity);
    }

    @Test
    void to_entity_should_map_version_jira_bean_with_released() {
        // Given
        Date releasedDate = new Date();
        VersionJiraBean versionJiraBean = new VersionJiraBean()
                .setId("123")
                .setName("V1.1")
                .setReleased(Boolean.TRUE)
                .setReleaseDate(releasedDate);

        // When
        VersionEntity versionEntity = versionMapper.toEntity(versionJiraBean);

        // Then
        VersionEntity expectedVersionEntity = new VersionEntity()
                .setId(new CompositeIdBaseEntity().setClientId("123"))
                .setName("V1.1")
                .setReleased(Boolean.TRUE)
                .setReleaseDate(releasedDate.toInstant());

        assertThat(versionEntity).usingRecursiveComparison().isEqualTo(expectedVersionEntity);
    }

    @Test
    void to_entity_with_version_jira_bean_null() {
        // Given / When / Then
        assertThat(versionMapper.toEntity(null)).isNull();
    }
}
