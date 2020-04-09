package org.coffeeprojects.pmda.feature.component;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComponentMapper {

    @Mapping(target = "id.clientId", source = "id")
    ComponentEntity toEntity(ComponentJiraBean componentJiraBean);
}
