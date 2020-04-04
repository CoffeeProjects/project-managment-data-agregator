package org.coffeeprojects.pmda.feature.resolution;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResolutionMapper {

    ResolutionEntity toEntity(ResolutionJiraBean resolutionJiraBean);
}
