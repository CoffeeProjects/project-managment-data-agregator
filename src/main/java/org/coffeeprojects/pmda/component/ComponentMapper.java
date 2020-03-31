package org.coffeeprojects.pmda.component;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComponentMapper {

    ComponentEntity toEntity(ComponentJiraBean componentJiraBean);
}
