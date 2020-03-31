package org.coffeeprojects.pmda.resolution;

import org.coffeeprojects.pmda.status.StatusEntity;
import org.coffeeprojects.pmda.status.StatusJiraBean;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResolutionMapper {

    ResolutionEntity toEntity(ResolutionJiraBean resolutionJiraBean);
}
