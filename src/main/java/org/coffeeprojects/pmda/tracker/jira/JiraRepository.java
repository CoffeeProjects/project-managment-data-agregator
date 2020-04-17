package org.coffeeprojects.pmda.tracker.jira;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JiraRepository {

    private static final Logger log = LoggerFactory.getLogger(JiraRepository.class);

    private static final String SEARCH_MODIFIED_ISSUES_QUERIES = "project = \"%s\"";
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE = "project = \"%s\" AND updated >= \"%s\"";
    private static final String SEARCH_WITH_ISSUES_QUERIES = "key in (\"%s\")";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault());
    private static final String EXPAND = "changelog";
    private static final Integer MAX_RESULT = 100;

    private TrackerRouter trackerRouter;

    public JiraRepository(TrackerRouter trackerRouter) {
        this.trackerRouter = trackerRouter;
    }

    public ProjectJiraBean getProjectDetails(ProjectEntity projectEntity) {
        return ((JiraClient) trackerRouter.getTracker(projectEntity)).getProjectById(projectEntity.getId().getClientId());
    }

    public List<IssueJiraBean> getModifiedIssues(ProjectEntity projectEntity, String fields) {
        String jql;

        if (projectEntity.getLastCheck() != null) {
            jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE, projectEntity.getKey(), DATE_TIME_FORMATTER.format(projectEntity.getLastCheck()));
        } else {
            jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES, projectEntity.getKey());
        }

        log.error("ERROR HERE 1 - JQL : {} - FIELDS : {} - PROJECT ENTITY : {}", jql, fields, projectEntity.toString());

        return getIssuesFromJira(projectEntity, jql, fields);
    }

    public List<IssueJiraBean> getExistingIssues(ProjectEntity projectEntity, List<String> issuesKey, String fields) {
        String jql = String.format(SEARCH_WITH_ISSUES_QUERIES, StringUtils.join(issuesKey, "\",\""));
        return getIssuesFromJira(projectEntity, jql, fields);
    }

    private List<IssueJiraBean> getIssuesFromJira(ProjectEntity projectEntity, String jql, String fields) {
        Integer startAt = 0;
        List<IssueJiraBean> issueJiraBeans = new ArrayList();

        log.error("ERROR HERE 2 - trackerRouter : {}", trackerRouter);

        log.error("ERROR HERE 2.5 - getTracker : {}", trackerRouter.getTracker(projectEntity));

        SearchIssuesResultJiraBean searchIssuesResultJiraBean = ((JiraClient) trackerRouter.getTracker(projectEntity)).searchIssues(jql, EXPAND, fields, MAX_RESULT.toString(), startAt.toString());
        log.error("ERROR HERE 3 - searchIssuesResultJiraBean : {}", searchIssuesResultJiraBean);
        log.error("ERROR HERE 4 - searchIssuesResultJiraBean : {}", searchIssuesResultJiraBean.toString());
        double pages = Math.ceil((searchIssuesResultJiraBean.getTotal()).doubleValue() / (searchIssuesResultJiraBean.getMaxResults()).doubleValue());

        log.error("ERROR HERE 5 - pages : {}", pages);

        for (int i = 1; i <= pages; i++) {
            if (i > 1) {
                log.error("ERROR HERE 6");
                startAt = (MAX_RESULT.intValue() * i) + 1;
                searchIssuesResultJiraBean = ((JiraClient) trackerRouter.getTracker(projectEntity)).searchIssues(jql, EXPAND, fields, MAX_RESULT.toString(), startAt.toString());
                log.error("ERROR HERE 7 - searchIssuesResultJiraBean {}", searchIssuesResultJiraBean);
            }
            issueJiraBeans.addAll(searchIssuesResultJiraBean.getIssues());
        }

        return issueJiraBeans;
    }
}
