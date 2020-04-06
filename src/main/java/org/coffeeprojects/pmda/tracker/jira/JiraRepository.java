package org.coffeeprojects.pmda.tracker.jira;

import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JiraRepository {
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES = "project =\"%s\" AND updated >= \"%s\"";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault());

    @Autowired
    private TrackerRouter trackerRouter;

    public JiraRepository(TrackerRouter trackerRouter) {
        trackerRouter = trackerRouter;
    }

    public ProjectJiraBean getProjectDetails(ProjectEntity projectEntity) {
        return ((JiraClient) TrackerRouter.getClient(trackerRouter, projectEntity)).getProjectByKey(projectEntity.getKey());
    }

    public List<IssueJiraBean> getModifiedIssues(ProjectEntity projectEntity, Instant fromDate, String fields) {

        final String jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES, projectEntity.getKey(), DATE_TIME_FORMATTER.format(fromDate));
        final String expand = "changelog";
        final Integer maxResults = 100;
        Integer startAt = 0;
        List<IssueJiraBean> issueJiraBeans = new ArrayList();

        SearchIssuesResultJiraBean searchIssuesResultJiraBean = ((JiraClient) TrackerRouter.getClient(trackerRouter, projectEntity)).searchIssues(jql, expand, fields, maxResults.toString(), startAt.toString());
        double pages = Math.ceil((searchIssuesResultJiraBean.getTotal()).doubleValue() / (searchIssuesResultJiraBean.getMaxResults()).doubleValue());

        for (int i = 1; i <= pages; i++) {
            if (i > 1) {
                startAt = (maxResults.intValue() * i) + 1;
                searchIssuesResultJiraBean = ((JiraClient) TrackerRouter.getClient(trackerRouter, projectEntity)).searchIssues(jql, expand, fields, maxResults.toString(), startAt.toString());
            }
            issueJiraBeans.addAll(searchIssuesResultJiraBean.getIssues());
        }

        return issueJiraBeans;
    }
}
