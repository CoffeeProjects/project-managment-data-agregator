package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.feature.changelog.ChangelogEntity;
import org.coffeeprojects.pmda.feature.changelog.ChangelogMapper;
import org.coffeeprojects.pmda.feature.changelog.jirabean.ChangelogJiraBean;
import org.coffeeprojects.pmda.feature.component.ComponentMapper;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issuetype.IssueTypeMapper;
import org.coffeeprojects.pmda.feature.priority.PriorityMapper;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.resolution.ResolutionMapper;
import org.coffeeprojects.pmda.feature.sprint.SprintMapper;
import org.coffeeprojects.pmda.feature.status.StatusMapper;
import org.coffeeprojects.pmda.feature.user.UserMapper;
import org.coffeeprojects.pmda.feature.version.VersionMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {UserMapper.class, StatusMapper.class, ResolutionMapper.class,
        PriorityMapper.class, IssueTypeMapper.class, ProjectMapper.class, VersionMapper.class,
        ComponentMapper.class, SprintMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IssueMapper {

    ChangelogMapper CHANGELOG_MAPPER = Mappers.getMapper(ChangelogMapper.class);

    @Mapping(target = "id.clientId", source = "id")
    @Mapping(target = "assignee", source = "fields.assignee")
    @Mapping(target = "reporter", source = "fields.reporter")
    @Mapping(target = "creator", source = "fields.creator")
    @Mapping(target = "summary", source = "fields.summary")
    @Mapping(target = "status", source = "fields.status")
    @Mapping(target = "resolution", source = "fields.resolution")
    @Mapping(target = "resolutionDate", source = "fields.resolutionDate")
    @Mapping(target = "priority", source = "fields.priority")
    @Mapping(target = "issueType", source = "fields.issueType")
    @Mapping(target = "project", source = "fields.project")
    @Mapping(target = "fixVersions", source = "fields.fixVersions")
    @Mapping(target = "originalEstimateSeconds", source = "fields.timeTracking.originalEstimateSeconds")
    @Mapping(target = "remainingEstimateSeconds", source = "fields.timeTracking.remainingEstimateSeconds")
    @Mapping(target = "timeSpentSeconds", source = "fields.timeTracking.timeSpentSeconds")
    @Mapping(target = "labels", source = "fields.labels")
    @Mapping(target = "components", source = "fields.components")
    @Mapping(target = "created", source = "fields.created")
    @Mapping(target = "updated", source = "fields.updated")
    @Mapping(target = "changelog", qualifiedByName = "changelog")
    IssueEntity toEntity(IssueJiraBean issueJiraBean);

    @Named("changelog")
    default Set<ChangelogEntity> changelog(ChangelogJiraBean changelogJiraBean) {
        return CHANGELOG_MAPPER.toEntities(changelogJiraBean);
    }
}