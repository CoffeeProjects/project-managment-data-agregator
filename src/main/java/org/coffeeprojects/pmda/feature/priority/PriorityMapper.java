package org.coffeeprojects.pmda.feature.priority;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriorityMapper {

    @Mapping(target = "id.storageId", source = "id")
    PriorityEntity toEntity(PriorityJiraBean priorityJiraBean);
}
