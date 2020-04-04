package org.coffeeprojects.pmda.version;

import org.coffeeprojects.pmda.user.UserEntity;
import org.coffeeprojects.pmda.user.UserJiraBean;
import org.coffeeprojects.pmda.user.UserMapper;
import org.coffeeprojects.pmda.user.UserMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = VersionMapperImpl.class)
@RunWith(SpringRunner.class)
public class VersionMapperTest {

    @Autowired
    private VersionMapper versionMapper;

    @Test
    public void to_entity_should_map_version_jira_bean_to_user_entity() {
        // Given
        VersionJiraBean versionJiraBean = new VersionJiraBean()
                .setId("1")
                .setName("V1.1");

        // When
        VersionEntity versionEntity = versionMapper.toEntity(versionJiraBean);

        // Then
        VersionEntity expectedVersionEntity = ((VersionEntity) new VersionEntity()
                .setId("1"))
                .setName("V1.1");
        assertThat(versionEntity).isEqualToComparingFieldByField(expectedVersionEntity);
    }
}
