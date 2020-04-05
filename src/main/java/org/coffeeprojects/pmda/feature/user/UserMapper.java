package org.coffeeprojects.pmda.feature.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id.storageId", source = "accountId")
    UserEntity toEntity(UserJiraBean userJiraBean);
}
