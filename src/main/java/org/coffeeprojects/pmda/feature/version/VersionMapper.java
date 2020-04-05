package org.coffeeprojects.pmda.feature.version;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VersionMapper {

    @Mapping(target = "id.storageId", source = "id")
    VersionEntity toEntity(VersionJiraBean versionJiraBean);
}
