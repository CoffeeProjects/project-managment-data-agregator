package org.coffeeprojects.pmda.tracker.jira;

import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectUtils;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.tracker.ExternalApiCallException;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JiraRepository {

    private static final String SEARCH_MODIFIED_ISSUES_QUERIES = "project = \"%s\"";
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE = "project = \"%s\" AND updated >= \"%s\"";
    private static final String SEARCH_WITH_ISSUES_QUERIES = "key in (\"%s\")";
    private static final String ERROR_WHEN_CALLING_API = "Problem when calling the remote API with this project : ";
    private static final String ERROR_MORE_DETAILS = "More details : ";
    private static final String ERROR_SET_ADMINISTRATOR = "More details : ";

    private static final String EXPAND = "changelog";
    private static final Integer MAX_RESULT = 100;
    private TrackerRouter trackerRouter;

    public JiraRepository(TrackerRouter trackerRouter) {
        this.trackerRouter = trackerRouter;
    }

    public UserJiraBean getUserDetails(ProjectEntity projectEntity) {
        if (projectEntity.getAdministrator() != null && projectEntity.getAdministrator().getId() != null &&
                projectEntity.getAdministrator().getId().getClientId() != null) {
            try {
                return ((JiraClient) trackerRouter.getTracker(projectEntity)).getUserById(projectEntity.getAdministrator().getId().getClientId());
            } catch (FeignException e) {
                throw new ExternalApiCallException(ERROR_WHEN_CALLING_API + projectEntity + ERROR_MORE_DETAILS + e.getMessage());
            }
        } else {
            throw new ExternalApiCallException(ERROR_SET_ADMINISTRATOR + projectEntity);
        }
    }

    public ProjectJiraBean getProjectDetails(ProjectEntity projectEntity) {
        try {
            return ((JiraClient) trackerRouter.getTracker(projectEntity)).getProjectById(projectEntity.getId().getClientId());
        } catch (FeignException e) {
            throw new ExternalApiCallException(ERROR_WHEN_CALLING_API + projectEntity);
        }
    }

    public List<IssueJiraBean> getModifiedIssues(ProjectEntity projectEntity, String fields) {
        String jql;

        if (projectEntity.getLastCheck() != null) {
            String lastCheckWithLocale = ProjectUtils.getLastCheckWithTimeZone(projectEntity.getLastCheck(), projectEntity.getAdministrator().getTimeZone());
            jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE, projectEntity.getKey(), lastCheckWithLocale);
        } else {
            jql = String.format(SEARCH_MODIFIED_ISSUES_QUERIES, projectEntity.getKey());
        }

        return getIssuesFromJira(projectEntity, jql, fields);
    }

    public List<IssueJiraBean> getExistingIssues(ProjectEntity projectEntity, List<String> issuesKey, String fields) {
        String jql = String.format(SEARCH_WITH_ISSUES_QUERIES, StringUtils.join(issuesKey, "\",\""));
        return getIssuesFromJira(projectEntity, jql, fields);
    }

    private List<IssueJiraBean> getIssuesFromJira(ProjectEntity projectEntity, String jql, String fields) {
        Integer startAt = 0;
        List<IssueJiraBean> issueJiraBeans = new ArrayList<>();
        SearchIssuesResultJiraBean searchIssuesResultJiraBean = null;

        try {
            searchIssuesResultJiraBean = ((JiraClient) trackerRouter.getTracker(projectEntity)).searchIssues(jql, EXPAND, fields, MAX_RESULT.toString(), startAt.toString());
        } catch (FeignException e) {
            throw new ExternalApiCallException(ERROR_WHEN_CALLING_API + projectEntity);
        }
        double pages = Math.ceil((searchIssuesResultJiraBean.getTotal()).doubleValue() / (searchIssuesResultJiraBean.getMaxResults()).doubleValue());

        for (int i = 1; i <= pages; i++) {
            if (i > 1) {
                startAt = (MAX_RESULT.intValue() * i) + 1;
                try {
                    searchIssuesResultJiraBean = ((JiraClient) trackerRouter.getTracker(projectEntity)).searchIssues(jql, EXPAND, fields, MAX_RESULT.toString(), startAt.toString());
                } catch (FeignException e) {
                    throw new ExternalApiCallException(ERROR_WHEN_CALLING_API + projectEntity);
                }
            }
            issueJiraBeans.addAll(searchIssuesResultJiraBean.getIssues());
        }

        return issueJiraBeans;
    }
}
