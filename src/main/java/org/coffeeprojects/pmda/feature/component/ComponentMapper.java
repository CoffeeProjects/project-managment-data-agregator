package org.coffeeprojects.pmda.feature.component;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComponentMapper {

    ComponentEntity toEntity(ComponentJiraBean componentJiraBean);
}
