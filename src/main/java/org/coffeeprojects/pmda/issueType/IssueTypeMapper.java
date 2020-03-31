package org.coffeeprojects.pmda.issueType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IssueTypeMapper {

    IssueTypeEntity toEntity(IssueTypeJiraBean issueTypeJiraBean);
}
