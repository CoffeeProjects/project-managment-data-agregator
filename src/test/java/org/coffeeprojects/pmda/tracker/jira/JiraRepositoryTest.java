package org.coffeeprojects.pmda.tracker.jira;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class JiraRepositoryTest {

    @Mock
    private TrackerRouter trackerRouter;

    @Mock
    private JiraClient jiraClient;

    private JiraRepository jiraRepository;

    @Before
    public void setup() {
        jiraRepository = new JiraRepository(trackerRouter);
    }

    @Test
    public void test_get_modified_issues_with_empty_list_return() {
        // Given
        Instant lastCheckDate = Instant.parse("2020-03-29T09:15:24.00Z"); // = 11h15 fr

        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setLastCheck(lastCheckDate)
                .setKey("pmda");

        String expand = "changelog";
        String fields = "summary,issuetype";
        String maxResults = "100";
        String startAt = "0";

        List<IssueJiraBean> issues = Arrays.asList();
        SearchIssuesResultJiraBean searchIssuesResultJiraBean = new SearchIssuesResultJiraBean()
                .setTotal(2L)
                .setMaxResults(2L)
                .setStartAt(0L)
                .setIssues(issues);

        String jql = "project =\"pmda\" AND updated >= \"2020-03-29 11:15\"";

        when(trackerRouter.getTracker(projectEntity)).thenReturn(jiraClient);
        when(jiraClient.searchIssues(eq(jql), eq(expand), eq(fields), eq(maxResults), eq(startAt))).thenReturn(searchIssuesResultJiraBean);

        // When
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity, fields);

        // Then
        assertThat(issueJiraBeans).isNotNull();
        assertThat(issueJiraBeans).isEmpty();
    }

    @Test
    public void test_get_modified_issues_should_return_issues() {
        // Given
        Instant lastCheckDate = Instant.parse("2020-03-29T09:15:24.00Z"); // = 11h15 fr

        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setLastCheck(lastCheckDate)
                .setKey("pmda");

        String expand = "changelog";
        String fields = "summary,issuetype";
        String maxResults = "100";
        String startAt = "0";

        List<IssueJiraBean> issues = Arrays.asList(
                new IssueJiraBean().setId("id1").setKey("key1"),
                new IssueJiraBean().setId("id2").setKey("key2")
        );
        SearchIssuesResultJiraBean searchIssuesResultJiraBean = new SearchIssuesResultJiraBean()
                .setTotal(2L)
                .setMaxResults(2L)
                .setStartAt(0L)
                .setIssues(issues);

        String jql = "project =\"pmda\" AND updated >= \"2020-03-29 11:15\"";

        when(trackerRouter.getTracker(projectEntity)).thenReturn(jiraClient);
        when(jiraClient.searchIssues(eq(jql), eq(expand), eq(fields), eq(maxResults), eq(startAt))).thenReturn(searchIssuesResultJiraBean);

        // When
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity, fields);

        // Then
        assertThat(issueJiraBeans).isNotNull();
        assertThat(issueJiraBeans).usingRecursiveFieldByFieldElementComparator().isEqualTo(searchIssuesResultJiraBean.getIssues());
    }


    @Test
    public void test_get_existing_issues() {
        // Given
        Instant lastCheckDate = Instant.parse("2020-03-29T09:15:24.00Z"); // = 11h15 fr

        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setLastCheck(lastCheckDate)
                .setKey("pmda");

        String expand = "changelog";
        String fields = "summary,issuetype";
        String maxResults = "100";
        String startAt = "0";

        List<IssueJiraBean> issues = Arrays.asList(
                new IssueJiraBean().setId("id1").setKey("key1"),
                new IssueJiraBean().setId("id2").setKey("key2")
        );
        SearchIssuesResultJiraBean searchIssuesResultJiraBean = new SearchIssuesResultJiraBean()
                .setTotal(2L)
                .setMaxResults(2L)
                .setStartAt(0L)
                .setIssues(issues);

        String jql = "id in (\"id1\",\"id2\")";

        when(trackerRouter.getTracker(projectEntity)).thenReturn(jiraClient);
        when(jiraClient.searchIssues(eq(jql), eq(expand), eq(fields), eq(maxResults), eq(startAt))).thenReturn(searchIssuesResultJiraBean);

        // When
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getExistingIssues(projectEntity, Arrays.asList("id1","id2"), fields);

        // Then
        assertThat(issueJiraBeans).isNotNull();
        assertThat(issueJiraBeans).usingRecursiveFieldByFieldElementComparator().isEqualTo(searchIssuesResultJiraBean.getIssues());
    }
}
