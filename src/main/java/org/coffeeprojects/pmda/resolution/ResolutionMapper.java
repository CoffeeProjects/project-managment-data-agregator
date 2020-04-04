package org.coffeeprojects.pmda.resolution;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResolutionMapper {

    ResolutionEntity toEntity(ResolutionJiraBean resolutionJiraBean);
}
