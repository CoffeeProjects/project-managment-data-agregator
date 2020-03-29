package org.coffeeprojects.pmda.tracker.jira;

import org.coffeeprojects.pmda.issue.IssueJiraBean;
import org.coffeeprojects.pmda.issue.SearchIssuesResultJiraBean;
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
import static org.mockito.Mockito.*;

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
                new IssueJiraBean().setId("id1").setKey("key1")
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
}
