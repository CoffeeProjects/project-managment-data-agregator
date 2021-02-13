package org.coffeeprojects.pmda.tracker.jira;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class JiraRepositoryTest {

    @Mock
    private TrackerRouter trackerRouter;

    @Mock
    private JiraClient jiraClient;

    private JiraRepository jiraRepository;

    @BeforeEach
    public void setup() {
        jiraRepository = new JiraRepository(trackerRouter);
    }

    @Test
    void get_user_detail_should_return_the_project_admin_detail() {
        // Given
        String clientId = "999";
        ProjectEntity projectEntity = new ProjectEntity()
                .setAdministrator(new UserEntity()
                        .setId(new CompositeIdBaseEntity().setClientId(clientId)));

        when(trackerRouter.getTracker(projectEntity)).thenReturn(jiraClient);

        UserJiraBean expectedUserJiraBean = new UserJiraBean()
                .setAccountId(clientId)
                .setDisplayName("Toto");

        when(jiraClient.getUserById(clientId)).thenReturn(expectedUserJiraBean);

        // When
        UserJiraBean userJiraBean = jiraRepository.getUserDetails(projectEntity);

        // Then
        assertThat(userJiraBean).isEqualToComparingFieldByField(expectedUserJiraBean);
    }

    @Test
    void get_project_details_should_return_the_project() {
        // Given
        String clientId = "123";
        ProjectEntity projectEntity = new ProjectEntity()
                .setId(new CompositeIdBaseEntity().setClientId(clientId));

        when(trackerRouter.getTracker(projectEntity)).thenReturn(jiraClient);

        ProjectJiraBean expectedProjectJiraBean = new ProjectJiraBean()
                .setId(clientId);
        when(jiraClient.getProjectById(clientId)).thenReturn(expectedProjectJiraBean);

        // When
        ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(projectEntity);

        // Then
        assertThat(projectJiraBean).isEqualToComparingFieldByField(expectedProjectJiraBean);
    }


    @Test
    void get_modified_issues_should_return_issues() {
        // Given
        Instant lastCheckDate = Instant.parse("2020-03-29T09:15:24.00Z"); // = 11h15 fr

        CompositeIdBaseEntity userId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA);
        UserEntity userEntity = new UserEntity().setId(userId)
                .setTimeZone("Europe/Paris");

        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA);
        ProjectEntity projectEntity = new ProjectEntity().setId(projectId)
                .setLastCheck(lastCheckDate)
                .setKey("pmda")
                .setAdministrator(userEntity);

        String expand = "changelog";
        String fields = "summary,issuetype";
        String maxResults = "50";
        String startAt = "0";

        List<IssueJiraBean> issues1 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            issues1.add(new IssueJiraBean().setId("id" + i).setKey("key" + i));
        }

        SearchIssuesResultJiraBean searchIssuesResultJiraBean1 = new SearchIssuesResultJiraBean()
                .setTotal(52L)
                .setMaxResults(50L)
                .setStartAt(0L)
                .setIssues(issues1);

        List<IssueJiraBean> issues2 = Arrays.asList(
                new IssueJiraBean().setId("id50").setKey("key50"),
                new IssueJiraBean().setId("id51").setKey("key51")
        );

        SearchIssuesResultJiraBean searchIssuesResultJiraBean2 = new SearchIssuesResultJiraBean()
                .setTotal(52L)
                .setMaxResults(2L)
                .setStartAt(50L)
                .setIssues(issues2);


        String jql = "project = \"pmda\" AND updated >= \"2020-03-29 11:15\"";

        when(trackerRouter.getTracker(projectEntity)).thenReturn(jiraClient);
        when(jiraClient.searchIssues(jql, expand, fields, maxResults, startAt)).thenReturn(searchIssuesResultJiraBean1);
        when(jiraClient.searchIssues(jql, expand, fields, maxResults, "50")).thenReturn(searchIssuesResultJiraBean2);

        // When
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity, fields);

        // Then
        assertThat(issueJiraBeans).isNotNull().hasSize(52);
        assertThat(issueJiraBeans.get(0).getId()).isEqualTo("id0");
        assertThat(issueJiraBeans.get(51).getId()).isEqualTo("id51");
    }


    @Test
    void get_existing_issues_should_return_issues() {
        // Given
        Instant lastCheckDate = Instant.parse("2020-03-29T09:15:24.00Z"); // = 11h15 fr

        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA);
        ProjectEntity projectEntity = new ProjectEntity().setId(projectId)
                .setLastCheck(lastCheckDate)
                .setKey("pmda");

        String expand = "changelog";
        String fields = "summary,issuetype";
        String maxResults = "50";
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

        String jql = "key in (\"KEY-1\",\"KEY-2\")";

        when(trackerRouter.getTracker(projectEntity)).thenReturn(jiraClient);
        when(jiraClient.searchIssues(jql, expand, fields, maxResults, startAt)).thenReturn(searchIssuesResultJiraBean);

        // When
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getExistingIssues(projectEntity, Arrays.asList("KEY-1", "KEY-2"), fields);

        // Then
        assertThat(issueJiraBeans).isNotNull();
        assertThat(issueJiraBeans).usingRecursiveFieldByFieldElementComparator().isEqualTo(searchIssuesResultJiraBean.getIssues());
    }
}
