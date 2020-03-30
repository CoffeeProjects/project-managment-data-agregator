package org.coffeeprojects.pmda.status;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    StatusEntity toEntity(StatusJiraBean statusJiraBean);
}