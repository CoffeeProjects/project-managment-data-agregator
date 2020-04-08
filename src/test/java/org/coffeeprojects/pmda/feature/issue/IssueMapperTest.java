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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

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

        assertThat(issueEntity.getSummary()).isEqualTo(expectedIssueEntity.getSummary());
        assertThat(issueEntity.getLabels().get(0)).isEqualTo(expectedIssueEntity.getLabels().get(0));
        assertThat(issueEntity.getLabels().get(1)).isEqualTo(expectedIssueEntity.getLabels().get(1));
    }

    @Test
    public void get_sprints_by_issue_jira_bean_with_sprints() {
        List<String> sprints = Arrays.asList(
                "com.atlassian.greenhopper.service.sprint.Sprint@2932643f[id=2,rapidViewId=1,state=FUTURE,name=PMDA ,goal=2 (%+\"'-$*€/\\|),goal=FPEfzefoç !!çà) ù%% ==+\nLoL \"'-$*€  ,\n/   \\ | Test( coucou,startDate=<null>,endDate=<null>,completeDate=<null>,sequence=2]"
        );
        FieldsJiraBean fieldsJiraBean = new FieldsJiraBean().setSprintsToString(sprints);
        IssueJiraBean issueJiraBean = new IssueJiraBean().setId("id1").setKey("key1").setFields(fieldsJiraBean);

        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        issueEntity.getSprints().stream()
                .forEach(p -> {
                    assertThat(p.getId().getStorageId().equals("2"));
                    assertThat(p.getRapidViewId()).isEqualTo("1");
                    assertThat(p.getState()).isEqualTo("FUTURE");
                    assertThat(p.getName()).isEqualTo("PMDA ,goal=2 (%+\"'-$*€/\\|)");
                    assertThat(p.getGoal()).isEqualTo("FPEfzefoç !!çà) ù%% ==+\nLoL \"'-$*€  ,\n/   \\ | Test( coucou");
                });

    }
}