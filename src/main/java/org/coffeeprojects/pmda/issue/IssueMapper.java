package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.component.ComponentMapper;
import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.IssueLinkJiraBean;
import org.coffeeprojects.pmda.issueType.IssueTypeMapper;
import org.coffeeprojects.pmda.priority.PriorityMapper;
import org.coffeeprojects.pmda.project.ProjectMapper;
import org.coffeeprojects.pmda.resolution.ResolutionMapper;
import org.coffeeprojects.pmda.sprint.SprintMapper;
import org.coffeeprojects.pmda.status.StatusMapper;
import org.coffeeprojects.pmda.user.UserMapper;
import org.coffeeprojects.pmda.version.VersionMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, StatusMapper.class, ResolutionMapper.class,
        PriorityMapper.class, IssueTypeMapper.class, ProjectMapper.class, VersionMapper.class,
        ComponentMapper.class, SprintMapper.class})
public interface IssueMapper {

    @Mapping(target = "assignee", source = "fields.assignee")
    @Mapping(target = "reporter", source = "fields.reporter")
    @Mapping(target = "creator", source = "fields.creator")
    @Mapping(target = "summary", source = "fields.summary")
    @Mapping(target = "status", source = "fields.status")
    @Mapping(target = "resolution", source = "fields.resolution")
    @Mapping(target = "priority", source = "fields.priority")
    @Mapping(target = "issueType", source = "fields.issueType")
    @Mapping(target = "project", source = "fields.project")
    @Mapping(target = "fixVersions", source = "fields.fixVersions")
    @Mapping(target = "labels", source = "fields.labels")
    @Mapping(target = "components", source = "fields.components")
    @Mapping(target = "created", source = "fields.created")
    @Mapping(target = "updated", source = "fields.updated")
    @Mapping(target = "issueLinks", source = "fields.issueLinks")
    @Mapping(target = "sprints", source = "fields.sprints")
    IssueEntity toEntity(IssueJiraBean issueJiraBean);

    @IterableMapping(elementTargetType = IssueEntity.class)
    IssueEntity toEntity(IssueLinkJiraBean issueLinkJiraBean);
}
