package org.coffeeprojects.pmda.sprint;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SprintMapper {

    SprintEntity toEntity(SprintJiraBean sprintJiraBean);
}
