package org.coffeeprojects.pmda.feature.user;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void to_entity_should_map_user_jira_bean_to_user_entity() {
        // Given
        UserJiraBean userJiraBean = new UserJiraBean()
                .setAccountId("123")
                .setEmailAddress("bruce_wayne@yopmail.com")
                .setDisplayName("Bruce Wayne")
                .setActive(true);

        // When
        UserEntity userEntity = userMapper.toEntity(userJiraBean);

        // Then
        UserEntity expectedUserEntity = new UserEntity()
                .setId(new CompositeIdBaseEntity().setClientId("123"))
                .setEmailAddress("bruce_wayne@yopmail.com")
                .setDisplayName("Bruce Wayne")
                .setActive(true);

        assertThat(userEntity).isEqualToComparingFieldByField(expectedUserEntity);
    }
}
