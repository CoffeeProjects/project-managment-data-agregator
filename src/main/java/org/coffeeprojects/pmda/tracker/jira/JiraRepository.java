package org.coffeeprojects.pmda.tracker.jira;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.issue.IssueJiraBean;
import org.coffeeprojects.pmda.issue.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.sprint.SprintJiraBean;
import org.coffeeprojects.pmda.tracker.jira.proxy.JiraProxy;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class JiraRepository {
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES = "project in (%s) AND updated >= \"%s\" ORDER BY updated ASC";
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

    public static List<SprintJiraBean> getSprintsByIssueJiraBean(IssueJiraBean issueJiraBean) {
        List<SprintJiraBean> sprintJiraBeans = new ArrayList<>();

        if (issueJiraBean != null && issueJiraBean.getFields() != null && issueJiraBean.getFields().getSprints() != null) {
                for (String sprint : issueJiraBean.getFields().getSprints()) {
                    SprintJiraBean sprintJiraBean = new SprintJiraBean();
                    String id = StringUtils.substringAfter(sprint, "id=");
                    String rapidView = StringUtils.substringAfterLast(id, ",rapidViewId=");
                    String state = StringUtils.substringAfterLast(sprint, ",state=");
                    String name = StringUtils.substringAfterLast(sprint, ",name=");
                    String goal = StringUtils.substringAfterLast(sprint, ",goal=");
                    String startDate = StringUtils.substringAfterLast(sprint, ",startDate=");
                    String endDate = StringUtils.substringAfterLast(sprint, ",endDate=");
                    String completeDate = StringUtils.substringAfterLast(sprint, ",completeDate=");

                    // Set Bean
                    sprintJiraBean.setId(StringUtils.replace(id, ",rapidViewId=" + rapidView, StringUtils.EMPTY));
                    sprintJiraBean.setRapidViewId(StringUtils.replace(rapidView, ",state=" + state, StringUtils.EMPTY));
                    sprintJiraBean.setState(StringUtils.replace(state, ",name=" + name, StringUtils.EMPTY));
                    sprintJiraBean.setName(StringUtils.replace(name, ",goal=" + goal, StringUtils.EMPTY));
                    sprintJiraBean.setGoal(StringUtils.replace(goal, ",startDate=" + startDate, StringUtils.EMPTY));
                    sprintJiraBean.setStartDate(getDateWithTimezone(StringUtils.replace(startDate, ",endDate=" + endDate, StringUtils.EMPTY)));
                    sprintJiraBean.setEndDate(getDateWithTimezone(StringUtils.replace(endDate, ",completeDate=" + completeDate, StringUtils.EMPTY)));
                    sprintJiraBean.setCompleteDate(getDateWithTimezone(StringUtils.substringAfterLast(completeDate, ",completeDate=")));
                }
        }
        return sprintJiraBeans;
    }

    private static Date getDateWithTimezone(String timezone) {
        Date date = null;

        SimpleDateFormat startDateTZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        startDateTZ.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            if (timezone != null && !timezone.isBlank() && !"<null>".equals(timezone)) {
                date = startDateTZ.parse(timezone);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
