package org.coffeeprojects.pmda.tracker.jira;

import org.coffeeprojects.pmda.issue.jirabean.FieldsJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.sprint.SprintJiraBean;
import org.coffeeprojects.pmda.tracker.jira.proxy.JiraProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JiraRepositoryTest {

    @Mock
    private JiraProxy jiraProxy;

    private JiraRepository jiraRepository;

    @Before
    public void setup() {
        jiraRepository = new JiraRepository(jiraProxy);
    }

    @Test
    public void get_modified_issues_should_return_issues() {
        // Given
        String projectName = "pmda";
        Instant lastModifiedDate = Instant.parse("2020-03-29T09:15:24.00Z"); // = 11h15 fr
        String expand = "schema,names";
        String fields = "summary,issuetype";

        List<IssueJiraBean> issues = Arrays.asList(
                new IssueJiraBean().setId("id1").setKey("key1"),
                new IssueJiraBean().setId("id2").setKey("key2")
        );
        SearchIssuesResultJiraBean searchIssuesResultJiraBean = new SearchIssuesResultJiraBean()
                .setTotal(2L)
                .setMaxResults(2L)
                .setStartAt(0L)
                .setIssues(issues);

        String jql = "project in (pmda) AND updated >= \"2020-03-29 11:15\"";
        when(jiraProxy.searchIssues(jql, expand, fields)).thenReturn(searchIssuesResultJiraBean);

        // When
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectName, lastModifiedDate, expand, fields);

        // Then
        assertThat(issueJiraBeans).isEqualTo(issueJiraBeans);
    }

    // getSprintsByIssueJiraBean
    @Test
    public void get_sprints_by_issue_jira_bean_null() {
        List<SprintJiraBean> sprintJiraBeans = jiraRepository.getSprintsByIssueJiraBean(null);

        // Then
        assertThat(sprintJiraBeans.size()).isEqualTo(0);
    }

    @Test
    public void get_sprints_by_issue_jira_bean_no_fields() {
        IssueJiraBean issueJiraBean = new IssueJiraBean().setId("id1").setKey("key1");
        List<SprintJiraBean> sprintJiraBeans = jiraRepository.getSprintsByIssueJiraBean(issueJiraBean);

        // Then
        assertThat(sprintJiraBeans.size()).isEqualTo(0);
    }

    @Test
    public void get_sprints_by_issue_jira_bean_no_sprints() {
        IssueJiraBean issueJiraBean = new IssueJiraBean().setId("id1").setKey("key1").setFields(new FieldsJiraBean());
        List<SprintJiraBean> sprintJiraBeans = jiraRepository.getSprintsByIssueJiraBean(issueJiraBean);

        // Then
        assertThat(sprintJiraBeans.size()).isEqualTo(0);
    }

    @Test
    public void get_sprints_by_issue_jira_bean_with_sprints() {
        List<String> sprints = Arrays.asList(
                "com.atlassian.greenhopper.service.sprint.Sprint@31b5556b[id=1,rapidViewId=1,state=CLOSED,name=PMDA Sprint 1,goal=,startDate=2020-03-25T20:06:44.960Z,endDate=2020-03-28T21:06:00.000Z,completeDate=2020-03-28T20:06:50.868Z,sequence=1]",
                "com.atlassian.greenhopper.service.sprint.Sprint@2932643f[id=2,rapidViewId=1,state=FUTURE,name=PMDA ,goal=2 (%+\"'-$*€/\\|),goal=FPEfzefoç !!çà) ù%% ==+\nLoL \"'-$*€  ,\n/   \\ | Test( coucou,startDate=<null>,endDate=<null>,completeDate=<null>,sequence=2]"
        );
        FieldsJiraBean fieldsJiraBean = new FieldsJiraBean().setSprints(sprints);
        IssueJiraBean issueJiraBean = new IssueJiraBean().setId("id1").setKey("key1").setFields(fieldsJiraBean);

        List<SprintJiraBean> sprintJiraBeans = jiraRepository.getSprintsByIssueJiraBean(issueJiraBean);

        // Then
        assertThat(sprintJiraBeans.size()).isEqualTo(2);
    }
}
