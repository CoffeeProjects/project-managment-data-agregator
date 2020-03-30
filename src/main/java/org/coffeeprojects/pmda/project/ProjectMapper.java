package org.coffeeprojects.pmda.project;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectEntity toEntity(ProjectJiraBean projectJiraBean);
}
