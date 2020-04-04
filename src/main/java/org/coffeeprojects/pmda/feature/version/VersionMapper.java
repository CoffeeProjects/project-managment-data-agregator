package org.coffeeprojects.pmda.feature.version;

import org.coffeeprojects.pmda.feature.project.ProjectEnum;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VersionMapper {

    VersionEntity toEntity(VersionJiraBean versionJiraBean);

    @AfterMapping
    default void fromIssueJiraBeanToIssueEntity(@MappingTarget VersionEntity output) {
        // Prefix ID (gestion des conflits entre trackers)
        output.setId(ProjectEnum.JIRA.name() + "-" + output.getId());
    }
}
