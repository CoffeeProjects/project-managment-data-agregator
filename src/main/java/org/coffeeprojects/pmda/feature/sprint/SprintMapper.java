package org.coffeeprojects.pmda.feature.sprint;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SprintMapper {

    @Mapping(target = "id.storageId", source = "id")
    SprintEntity toEntity(SprintJiraBean sprintJiraBean);
}
