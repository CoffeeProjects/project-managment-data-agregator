package org.coffeeprojects.pmda.feature.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_to_entity_should_map_user_jira_bean_to_user_entity() {
        // Given
        UserJiraBean userJiraBean = new UserJiraBean()
                .setEmailAddress("bruce_wayne@yopmail.com")
                .setDisplayName("Bruce Wayne")
                .setActive(true);

        // When
        UserEntity userEntity = userMapper.toEntity(userJiraBean);

        // Then
        UserEntity expectedUserEntity = new UserEntity()
                .setEmailAddress("bruce_wayne@yopmail.com")
                .setDisplayName("Bruce Wayne")
                .setActive(true);

        assertThat(userEntity.getEmailAddress()).isEqualTo(expectedUserEntity.getEmailAddress());
        assertThat(userEntity.getDisplayName()).isEqualTo(expectedUserEntity.getDisplayName());
        assertThat(userEntity.isActive()).isEqualTo(expectedUserEntity.isActive());
    }
}
