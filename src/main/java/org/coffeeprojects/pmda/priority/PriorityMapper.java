package org.coffeeprojects.pmda.priority;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriorityMapper {

    PriorityEntity toEntity(PriorityJiraBean priorityJiraBean);
}
