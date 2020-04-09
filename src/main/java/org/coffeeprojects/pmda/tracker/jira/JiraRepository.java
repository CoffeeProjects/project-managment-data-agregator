package org.coffeeprojects.pmda.tracker.jira;

import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JiraRepository {
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES = "project =\"%s\"";
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE = "project =\"%s\" AND updated >= \"%s\"";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault());
    private static final String EXPAND = "changelog";
    private static final Integer MAX_RESULT = 100;

    private TrackerRouter trackerRouter;

    public JiraRepository(TrackerRouter trackerRouter) {
        this.trackerRouter = trackerRouter;
    }

    public ProjectJiraBean getProjectDetails(ProjectEntity projectEntity) {
        return ((JiraClient) TrackerRouter.getTracker(trackerRouter, projectEntity)).getProjectByKey(projectEntity.getKey());
    }

    public List<IssueJiraBean> getModifiedIssues(ProjectEntity projectEntity, String fields) {
        Integer startAt = 0;
        List<IssueJiraBean> issueJiraBeans = new ArrayList();
        String jql;

        if (projectEntity.getLastCheck() != null) {
            jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE, projectEntity.getKey(), DATE_TIME_FORMATTER.format(projectEntity.getLastCheck()));
        } else {
            jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES, projectEntity.getKey());
        }

        SearchIssuesResultJiraBean searchIssuesResultJiraBean = ((JiraClient) TrackerRouter.getTracker(trackerRouter, projectEntity)).searchIssues(jql, EXPAND, fields, MAX_RESULT.toString(), startAt.toString());
        double pages = Math.ceil((searchIssuesResultJiraBean.getTotal()).doubleValue() / (searchIssuesResultJiraBean.getMaxResults()).doubleValue());

        for (int i = 1; i <= pages; i++) {
            if (i > 1) {
                startAt = (MAX_RESULT.intValue() * i) + 1;
                searchIssuesResultJiraBean = ((JiraClient) TrackerRouter.getTracker(trackerRouter, projectEntity)).searchIssues(jql, EXPAND, fields, MAX_RESULT.toString(), startAt.toString());
            }
            issueJiraBeans.addAll(searchIssuesResultJiraBean.getIssues());
        }

        return issueJiraBeans;
    }
}
