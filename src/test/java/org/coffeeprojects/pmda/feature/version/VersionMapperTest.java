package org.coffeeprojects.pmda.feature.version;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = VersionMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class VersionMapperTest {

    @Autowired
    private VersionMapper versionMapper;

    @Test
    public void test_to_entity_should_map_version_jira_bean_to_user_entity() {
        // Given
        VersionJiraBean versionJiraBean = new VersionJiraBean()
                .setName("V1.1");

        // When
        VersionEntity versionEntity = versionMapper.toEntity(versionJiraBean);

        // Then
        VersionEntity expectedVersionEntity = new VersionEntity()
                .setName("V1.1");

        assertThat(versionEntity.getName()).isEqualTo(expectedVersionEntity.getName());
    }
}
