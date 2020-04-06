package org.coffeeprojects.pmda.tracker.jira;

import feign.Target;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEnum;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class JiraRepositoryTest {

    @Mock
    private TrackerRouter trackerRouter;

    private JiraRepository jiraRepository;

    @Before
    public void setup() {
        jiraRepository = new JiraRepository(trackerRouter);
    }

    @Test
    @Ignore
    public void get_modified_issues_should_return_issues() {
        // Given
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setStorageId("1").setTrackerId("1").setTrackerType(ProjectEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("pmda");

        Instant lastModifiedDate = Instant.parse("2020-03-29T09:15:24.00Z"); // = 11h15 fr
        String expand = "schema,names";
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

        String jql = "project in (pmda) AND updated >= \"2020-03-29 11:15\"";
        mockStatic(TrackerRouter.class);
        when(TrackerRouter.getClient(Mockito.any(), Mockito.any())).thenReturn(new Target.HardCodedTarget(JiraClient.class, "http://pmda.orgr"));
        when(((JiraClient) TrackerRouter.getClient(trackerRouter, projectEntity)).searchIssues(jql, expand, fields, maxResults, startAt))
                .thenReturn(searchIssuesResultJiraBean);

        //when(jiraRepository.getSearchIssuesResultJiraBean(any(), any(), any(), any(), any(), any())).thenReturn()
        // When
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity, lastModifiedDate, fields);

        // Then
        assertThat(issueJiraBeans).isEqualTo(issueJiraBeans);
    }
}