package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.feature.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProjectMapper {

    @Mapping(target = "id.clientId", source = "id")
    @Mapping(target = "administrator", source = "lead")
    ProjectEntity toEntity(ProjectJiraBean projectJiraBean);
}
