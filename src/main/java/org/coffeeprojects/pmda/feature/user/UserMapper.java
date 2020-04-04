package org.coffeeprojects.pmda.feature.user;

import org.coffeeprojects.pmda.feature.project.ProjectEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "accountId")
    UserEntity toEntity(UserJiraBean userJiraBean);

    @AfterMapping
    default void fromIssueJiraBeanToIssueEntity(@MappingTarget UserEntity output) {
        // Prefix ID (gestion des conflits entre trackers)
        output.setId(ProjectEnum.JIRA.name() + "-" + output.getId());
    }
}
