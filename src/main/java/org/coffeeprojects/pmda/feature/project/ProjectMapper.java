package org.coffeeprojects.pmda.feature.project;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "id.storageId", source = "id")
    ProjectEntity toEntity(ProjectJiraBean projectJiraBean);
}
