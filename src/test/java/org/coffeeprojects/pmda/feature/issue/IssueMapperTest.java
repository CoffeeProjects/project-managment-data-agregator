package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.feature.component.ComponentMapperImpl;
import org.coffeeprojects.pmda.feature.issue.jirabean.FieldsJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issueType.IssueTypeMapperImpl;
import org.coffeeprojects.pmda.feature.priority.PriorityMapperImpl;
import org.coffeeprojects.pmda.feature.project.ProjectMapperImpl;
import org.coffeeprojects.pmda.feature.resolution.ResolutionMapperImpl;
import org.coffeeprojects.pmda.feature.sprint.SprintMapperImpl;
import org.coffeeprojects.pmda.feature.status.StatusMapperImpl;
import org.coffeeprojects.pmda.feature.user.UserMapperImpl;
import org.coffeeprojects.pmda.feature.version.VersionMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {UserMapperImpl.class, StatusMapperImpl.class, ResolutionMapperImpl.class,
        PriorityMapperImpl.class, IssueTypeMapperImpl.class, ProjectMapperImpl.class, VersionMapperImpl.class,
        ComponentMapperImpl.class, SprintMapperImpl.class, IssueMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class IssueMapperTest {
    @Autowired
    private IssueMapper issueMapper;

    @Test
    public void to_entity_should_map_issue_jira_bean_to_issue_entity() {

        // Given
        IssueJiraBean issueJiraBean = new IssueJiraBean()
                .setKey("key1")
                .setExpand("expand")
                .setFields(
                        new FieldsJiraBean()
                                .setSummary("summary")
                                .setLabels(Arrays.asList("test-label", "label2"))
                );

        // When
        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        IssueEntity expectedIssueEntity = new IssueEntity()
                .setSummary("summary")
                .setLabels(Arrays.asList("test-label", "label2"));

        assertThat(issueEntity).isEqualToComparingFieldByField(expectedIssueEntity);
    }
}
