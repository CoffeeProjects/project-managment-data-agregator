package org.coffeeprojects.pmda.feature.project;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "id.clientId", source = "id")
    ProjectEntity toEntity(ProjectJiraBean projectJiraBean);
}
