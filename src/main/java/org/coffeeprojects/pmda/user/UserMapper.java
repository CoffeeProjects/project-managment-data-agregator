package org.coffeeprojects.pmda.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserJiraBean userJiraBean);
}
