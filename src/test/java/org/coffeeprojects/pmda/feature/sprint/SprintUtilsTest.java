package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class SprintUtilsTest {

    @Test
    public void get_sprints_by_issue_jira_bean_with_sprints_null() {
        IssueEntity issueEntity = new IssueEntity();
        SprintUtils.toEntity(null, issueEntity);

        // Then
        assertThat(issueEntity.getSprints()).isNull();
    }

    @Test
    public void get_sprints_by_issue_jira_bean_with_sprints_empty() {
        List<String> sprints = Arrays.asList("");

        IssueEntity issueEntity = new IssueEntity();
        SprintUtils.toEntity(sprints, issueEntity);

        // Then
        assertThat(issueEntity.getSprints()).isNull();
    }

    @Test
    public void get_sprints_by_issue_jira_bean_with_sprints() {
        List<String> sprints = Arrays.asList(
                "com.atlassian.greenhopper.service.sprint.Sprint@2932643f[id=2,rapidViewId=1,state=FUTURE,name=PMDA ,goal=2 (%+\"'-$*€/\\|),goal=FPEfzefoç !!çà) ù%% ==+\nLoL \"'-$*€  ,\n/   \\ | Test( coucou,startDate=<null>,endDate=<null>,completeDate=<null>,sequence=2]"
        );

        IssueEntity issueEntity = new IssueEntity();
        SprintUtils.toEntity(sprints, issueEntity);

        // Then
        issueEntity.getSprints().stream()
                .forEach(p -> {
                    assertThat(p.getId().getClientId().equals("2"));
                    assertThat(p.getRapidViewId()).isEqualTo("1");
                    assertThat(p.getState()).isEqualTo("FUTURE");
                    assertThat(p.getName()).isEqualTo("PMDA ,goal=2 (%+\"'-$*€/\\|)");
                    assertThat(p.getGoal()).isEqualTo("FPEfzefoç !!çà) ù%% ==+\nLoL \"'-$*€  ,\n/   \\ | Test( coucou");
                });

    }
}
