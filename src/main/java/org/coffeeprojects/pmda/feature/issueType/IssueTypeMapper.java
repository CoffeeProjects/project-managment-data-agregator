package org.coffeeprojects.pmda.feature.issueType;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueTypeMapper {

    @Mapping(target = "id.clientId", source = "id")
    IssueTypeEntity toEntity(IssueTypeJiraBean issueTypeJiraBean);
}
