package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.component.ComponentEntity;
import org.coffeeprojects.pmda.component.ComponentJiraBean;
import org.coffeeprojects.pmda.component.ComponentMapperImpl;
import org.coffeeprojects.pmda.issue.jirabean.FieldsJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.IssueLinkJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.IssueLinkJiraTypeBean;
import org.coffeeprojects.pmda.issueType.IssueTypeEntity;
import org.coffeeprojects.pmda.issueType.IssueTypeJiraBean;
import org.coffeeprojects.pmda.issueType.IssueTypeMapperImpl;
import org.coffeeprojects.pmda.priority.PriorityEntity;
import org.coffeeprojects.pmda.priority.PriorityJiraBean;
import org.coffeeprojects.pmda.priority.PriorityMapperImpl;
import org.coffeeprojects.pmda.project.ProjectEntity;
import org.coffeeprojects.pmda.project.ProjectJiraBean;
import org.coffeeprojects.pmda.project.ProjectMapperImpl;
import org.coffeeprojects.pmda.resolution.ResolutionEntity;
import org.coffeeprojects.pmda.resolution.ResolutionJiraBean;
import org.coffeeprojects.pmda.resolution.ResolutionMapperImpl;
import org.coffeeprojects.pmda.sprint.SprintEntity;
import org.coffeeprojects.pmda.sprint.SprintJiraBean;
import org.coffeeprojects.pmda.sprint.SprintMapperImpl;
import org.coffeeprojects.pmda.status.StatusEntity;
import org.coffeeprojects.pmda.status.StatusJiraBean;
import org.coffeeprojects.pmda.status.StatusMapperImpl;
import org.coffeeprojects.pmda.user.UserEntity;
import org.coffeeprojects.pmda.user.UserJiraBean;
import org.coffeeprojects.pmda.user.UserMapperImpl;
import org.coffeeprojects.pmda.version.VersionEntity;
import org.coffeeprojects.pmda.version.VersionJiraBean;
import org.coffeeprojects.pmda.version.VersionMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {UserMapperImpl.class, StatusMapperImpl.class, ResolutionMapperImpl.class,
        PriorityMapperImpl.class, IssueTypeMapperImpl.class, ProjectMapperImpl.class, VersionMapperImpl.class,
        ComponentMapperImpl.class, SprintMapperImpl.class, IssueMapperImpl.class})
@RunWith(SpringRunner.class)
public class IssueMapperTest {
    @Autowired
    private IssueMapper issueMapper;

    @Test
    public void to_entity_should_map_issue_jira_bean_to_issue_entity() {
        // Given
        IssueJiraBean issueJiraBean = new IssueJiraBean()
                .setId("id1")
                .setKey("key1")
                .setExpand("expand")
                .setFields(
                        new FieldsJiraBean()
                                .setAssignee(new UserJiraBean().setAccountId("11111"))
                                .setReporter(new UserJiraBean().setAccountId("22222"))
                                .setCreator(new UserJiraBean().setAccountId("33333"))
                                .setSummary("summary")
                                .setStatus(new StatusJiraBean().setId("11"))
                                .setResolution(new ResolutionJiraBean().setId("1"))
                                .setPriority(new PriorityJiraBean().setId("12"))
                                .setIssueType(new IssueTypeJiraBean().setId("13"))
                                .setProject(new ProjectJiraBean().setId("14"))
                                .setFixVersions(Set.of(new VersionJiraBean().setId("15")))
                                .setLabels(Arrays.asList("test-label", "label2"))
                                .setComponents(Set.of(new ComponentJiraBean().setId("16")))
                                .setCreated(new Date())
                                .setUpdated(new Date())
                                // TODO: Mapping des issueLinks à faire
                                //.setIssueLinks(Set.of(new IssueLinkJiraBean().setInwardIssue(new IssueJiraBean().setId("17"))))
                                .setSprints(Set.of(new SprintJiraBean().setId("18")))
                );
        // TODO: completer le bean avec tous les champs

        // When
        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        IssueEntity expectedIssueEntity = new IssueEntity()
                .setId("id1")
                .setKey("key1")
                .setAssignee(new UserEntity().setAccountId("11111"))
                .setReporter(new UserEntity().setAccountId("22222"))
                .setCreator(new UserEntity().setAccountId("33333"))
                .setSummary("summary")
                .setStatus(new StatusEntity().setId("11"))
                .setResolution(new ResolutionEntity().setId("1"))
                .setPriority(new PriorityEntity().setId("12"))
                .setIssueType(new IssueTypeEntity().setId("13"))
                .setProject(new ProjectEntity().setId("14"))
                .setFixVersions(Set.of(new VersionEntity().setId("15")))
                .setLabels(Arrays.asList("test-label", "label2"))
                .setComponents(Set.of(new ComponentEntity().setId("16")))
                .setCreated(new Date())
                .setUpdated(new Date())
                //.setIssueLinks(Set.of(new IssueEntity().setId("17")))
                .setSprints(Set.of(new SprintEntity().setId("18")));

        assertThat(issueEntity).isEqualTo(expectedIssueEntity);
    }
}
