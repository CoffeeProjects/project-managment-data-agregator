package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.component.ComponentMapper;
import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.issueType.IssueTypeMapper;
import org.coffeeprojects.pmda.priority.PriorityMapper;
import org.coffeeprojects.pmda.project.ProjectMapper;
import org.coffeeprojects.pmda.resolution.ResolutionMapper;
import org.coffeeprojects.pmda.sprint.SprintMapper;
import org.coffeeprojects.pmda.status.StatusMapper;
import org.coffeeprojects.pmda.user.UserEntity;
import org.coffeeprojects.pmda.user.UserMapper;
import org.coffeeprojects.pmda.version.VersionMapper;
import org.mapstruct.*;

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
    @Mapping(target = "sprints", source = "fields.sprints")
    IssueEntity toEntity(IssueJiraBean issueJiraBean);

    @AfterMapping
    default void removeDuplicatesFromIssueJiraBeanToIssueEntity(@MappingTarget IssueEntity output) {
        UserEntity assignee = output.getAssignee();
        UserEntity creator = output.getCreator();
        UserEntity reporter = output.getReporter();

        if (output != null) {
            assignee = assignee != null && creator != null && assignee.getId().equals(creator.getId()) ? creator : assignee;
            assignee = assignee != null && reporter != null && assignee.getId().equals(reporter.getId()) ? reporter : assignee;
            creator = creator != null && reporter != null && creator.getId().equals(reporter.getId()) ? reporter : creator;
        }

        output.setAssignee(assignee);
        output.setCreator(creator);
        output.setReporter(reporter);
    }
}
