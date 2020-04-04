package org.coffeeprojects.pmda.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserMapperImpl.class)
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void to_entity_should_map_user_jira_bean_to_user_entity() {
        // Given
        UserJiraBean userJiraBean = new UserJiraBean()
                .setAccountId("id1")
                .setEmailAddress("bruce_wayne@yopmail.com")
                .setDisplayName("Bruce Wayne")
                .setActive(true);

        // When
        UserEntity userEntity = userMapper.toEntity(userJiraBean);

        // Then
        UserEntity expectedUserEntity = new UserEntity()
                .setId("id1")
                .setEmailAddress("bruce_wayne@yopmail.com")
                .setDisplayName("Bruce Wayne")
                .setActive(true);
        assertThat(userEntity).isEqualToComparingFieldByField(expectedUserEntity);
    }
}
