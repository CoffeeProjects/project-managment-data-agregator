package org.coffeeprojects.pmda.version;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VersionMapper {

    VersionEntity toEntity(VersionJiraBean versionJiraBean);
}
