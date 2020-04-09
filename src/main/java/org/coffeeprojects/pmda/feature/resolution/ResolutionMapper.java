package org.coffeeprojects.pmda.feature.resolution;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResolutionMapper {

    @Mapping(target = "id.clientId", source = "id")
    ResolutionEntity toEntity(ResolutionJiraBean resolutionJiraBean);
}
