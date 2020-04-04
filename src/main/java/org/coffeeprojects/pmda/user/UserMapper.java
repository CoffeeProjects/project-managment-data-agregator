package org.coffeeprojects.pmda.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "accountId")
    UserEntity toEntity(UserJiraBean userJiraBean);
}
