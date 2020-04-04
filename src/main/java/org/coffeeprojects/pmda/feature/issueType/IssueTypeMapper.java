package org.coffeeprojects.pmda.feature.issueType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IssueTypeMapper {

    IssueTypeEntity toEntity(IssueTypeJiraBean issueTypeJiraBean);
}
