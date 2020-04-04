package org.coffeeprojects.pmda.feature.sprint;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SprintMapper {

    SprintEntity toEntity(SprintJiraBean sprintJiraBean);
}
