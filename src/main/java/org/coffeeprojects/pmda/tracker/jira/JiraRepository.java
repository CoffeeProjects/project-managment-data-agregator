package org.coffeeprojects.pmda.tracker.jira;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.sprint.SprintJiraBean;
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

    public ProjectJiraBean getProjectDetails(String key) {
        return jiraProxy.getProjectByKey(key);
    }

    public List<IssueJiraBean> getModifiedIssues(String projectName, Instant fromDate, String fields) {

        final String jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES, projectName, DATE_TIME_FORMATTER.format(fromDate));
        final String expand = "changelog";
        final Integer maxResults = 100;
        Integer startAt = 0;
        List<IssueJiraBean> issueJiraBeans = new ArrayList();

        SearchIssuesResultJiraBean searchIssuesResultJiraBean = jiraProxy.searchIssues(jql, expand, fields, maxResults.toString(), startAt.toString());
        double pages = Math.ceil((searchIssuesResultJiraBean.getTotal()).doubleValue() / (searchIssuesResultJiraBean.getMaxResults()).doubleValue());

        for (int i = 1; i <= pages; i++) {
            if (i > 1) {
                startAt = (maxResults.intValue() * i) + 1;
                searchIssuesResultJiraBean = jiraProxy.searchIssues(jql, expand, fields, maxResults.toString(), startAt.toString());
            }
            for (IssueJiraBean issueJiraBean : searchIssuesResultJiraBean.getIssues()) {
                issueJiraBean.getFields().setSprints(getSprintsByIssueJiraBean(issueJiraBean));
            }
            issueJiraBeans.addAll(searchIssuesResultJiraBean.getIssues());
        }

        return issueJiraBeans;
    }

    Set<SprintJiraBean> getSprintsByIssueJiraBean(IssueJiraBean issueJiraBean) {
        Set<SprintJiraBean> sprintJiraBeans = new HashSet<>();

        if (issueJiraBean != null && issueJiraBean.getFields() != null && issueJiraBean.getFields().getSprintsToString() != null) {
                for (String sprint : issueJiraBean.getFields().getSprintsToString()) {
                    SprintJiraBean sprintJiraBean = new SprintJiraBean();
                    String id = StringUtils.substringAfter(sprint, "id=");
                    String rapidView = StringUtils.substringAfterLast(id, ",rapidViewId=");
                    String state = StringUtils.substringAfterLast(sprint, ",state=");
                    String name = StringUtils.substringAfterLast(sprint, ",name=");
                    String goal = StringUtils.substringAfterLast(sprint, ",goal=");
                    String startDate = StringUtils.substringAfterLast(sprint, ",startDate=");
                    String endDate = StringUtils.substringAfterLast(sprint, ",endDate=");
                    String completeDate = StringUtils.substringAfterLast(sprint, ",completeDate=");

                    sprintJiraBean.setId(StringUtils.replace(id, ",rapidViewId=" + rapidView, StringUtils.EMPTY));
                    sprintJiraBean.setRapidViewId(StringUtils.replace(rapidView, ",state=" + state, StringUtils.EMPTY));
                    sprintJiraBean.setState(StringUtils.replace(state, ",name=" + name, StringUtils.EMPTY));
                    sprintJiraBean.setName(StringUtils.replace(name, ",goal=" + goal, StringUtils.EMPTY));
                    sprintJiraBean.setGoal(StringUtils.replace(goal, ",startDate=" + startDate, StringUtils.EMPTY));
                    sprintJiraBean.setStartDate(getDateWithTimezone(StringUtils.replace(startDate, ",endDate=" + endDate, StringUtils.EMPTY)));
                    sprintJiraBean.setEndDate(getDateWithTimezone(StringUtils.replace(endDate, ",completeDate=" + completeDate, StringUtils.EMPTY)));
                    sprintJiraBean.setCompleteDate(getDateWithTimezone(StringUtils.substringAfterLast(completeDate, ",completeDate=")));

                    sprintJiraBeans.add(sprintJiraBean);
                }
        }
        return sprintJiraBeans;
    }

    private Date getDateWithTimezone(String timezone) {

        SimpleDateFormat startDateTZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        startDateTZ.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
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
