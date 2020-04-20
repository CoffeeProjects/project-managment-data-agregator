package org.coffeeprojects.pmda.feature.issuetypee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueTypeMapper {

    @Mapping(target = "id.clientId", source = "id")
    IssueTypeEntity toEntity(IssueTypeJiraBean issueTypeJiraBean);
}
