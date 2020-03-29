package org.coffeeprojects.pmda.tracker.jira;

import org.coffeeprojects.pmda.issue.IssueJiraBean;
import org.coffeeprojects.pmda.issue.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.tracker.jira.proxy.JiraProxy;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class JiraRepository {
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES = "project in (%s) AND updated >= \"%s\"";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault());
    private final JiraProxy jiraProxy;

    public JiraRepository(JiraProxy jiraProxy) {
        this.jiraProxy = jiraProxy;
    }

    public List<IssueJiraBean> getModifiedIssues(String projectName, Instant fromDate, String expand, String fields) {
        // TODO: verifier si on peut récuperer tous les issues en même temps
        final String jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES, projectName, DATE_TIME_FORMATTER.format(fromDate));
        final SearchIssuesResultJiraBean searchIssuesResultJiraBean = jiraProxy.searchIssues(jql, expand, fields);

        return searchIssuesResultJiraBean.getIssues();
    }
}
