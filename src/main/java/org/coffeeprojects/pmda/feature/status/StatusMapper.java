package org.coffeeprojects.pmda.feature.status;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    @Mapping(target = "id.storageId", source = "id")
    StatusEntity toEntity(StatusJiraBean statusJiraBean);
}
