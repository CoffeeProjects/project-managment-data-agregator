package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.status.StatusMapper;
import org.coffeeprojects.pmda.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, StatusMapper.class})
public interface IssueMapper {

    @Mapping(target = "assignee", source = "fields.assignee")
    @Mapping(target = "reporter", source = "fields.reporter")
    @Mapping(target = "creator", source = "fields.creator")
    @Mapping(target = "summary", source = "fields.summary")
    @Mapping(target = "status", source = "fields.status")
        // TODO: mapper non complet, ajouter les autres @Mapping
    IssueEntity toEntity(IssueJiraBean issueJiraBean);
}
