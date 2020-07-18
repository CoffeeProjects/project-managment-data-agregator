package org.coffeeprojects.pmda.tracker.jira;

import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.ExceptionConstant;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectUtils;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.tracker.ExternalApiCallException;
import org.coffeeprojects.pmda.tracker.TrackerRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JiraRepository {

    private static final String SEARCH_MODIFIED_ISSUES_QUERIES = "project = \"%s\"";
    private static final String SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE = "project = \"%s\" AND updated >= \"%s\"";
    private static final String SEARCH_WITH_ISSUES_QUERIES = "key in (\"%s\")";

    private static final String EXPAND = "changelog";
    private static final int MAX_RESULT = 50;

    private final TrackerRouter trackerRouter;

    private final Logger logger = LoggerFactory.getLogger(JiraRepository.class);

    public JiraRepository(TrackerRouter trackerRouter) {
        this.trackerRouter = trackerRouter;
    }

    /**
     * Get the admin user of a project
     *
     * @param projectEntity The project
     * @return The user or null
     */
    public UserJiraBean getUserDetails(ProjectEntity projectEntity) {
        logger.info("Get user admin from Jira of project: {}", projectEntity);

        String clientId = Optional.of(projectEntity)
                .map(ProjectEntity::getAdministrator)
                .map(UserEntity::getId)
                .map(CompositeIdBaseEntity::getClientId)
                .orElseThrow(() -> new ExternalApiCallException(ExceptionConstant.ERROR_SET_ADMINISTRATOR + projectEntity.getKey()));

        try {
            return ((JiraClient) trackerRouter.getTracker(projectEntity)).getUserById(clientId);
        } catch (FeignException e) {
            throw new ExternalApiCallException(ExceptionConstant.ERROR_API_CALL + projectEntity + ExceptionConstant.ERROR_MORE_DETAILS + e.getMessage(), e);
        }
    }

    /**
     * Get project details of a project from Jira
     *
     * @param projectEntity The project
     * @return The project or null
     */
    public ProjectJiraBean getProjectDetails(ProjectEntity projectEntity) {
        logger.info("Get project details from Jira of project: {}", projectEntity);

        try {
            return ((JiraClient) trackerRouter.getTracker(projectEntity)).getProjectById(projectEntity.getId().getClientId());
        } catch (FeignException e) {
            throw new ExternalApiCallException(ExceptionConstant.ERROR_API_CALL + projectEntity.getKey(), e);
        }
    }

    /**
     * Get modified issues of a project, since the last modified date (or all)
     *
     * @param projectEntity The project
     * @param fields        List of fields to retrieve
     * @return The issues
     */
    public List<IssueJiraBean> getModifiedIssues(ProjectEntity projectEntity, String fields) {
        logger.info("Get modified issues from Jira of project: {}, with fields: {}", projectEntity, fields);

        String jql = Optional.ofNullable(projectEntity.getLastCheck())
                .map(lastCheckDate -> ProjectUtils.getLastCheckWithTimeZone(projectEntity.getLastCheck(), projectEntity.getAdministrator().getTimeZone()))
                .map(lastCheckWithLocale -> String.format(SEARCH_MODIFIED_ISSUES_QUERIES_WITH_UPDATE, projectEntity.getKey(), lastCheckWithLocale))
                .orElse(String.format(SEARCH_MODIFIED_ISSUES_QUERIES, projectEntity.getKey()));

        return getIssuesFromJira(projectEntity, jql, fields);
    }

    /**
     * Get issues of a project, filtered by a list of issue key
     *
     * @param projectEntity The project
     * @param issuesKey     The list of issue key
     * @param fields        List of fields to retrieve
     * @return The issues
     */
    public List<IssueJiraBean> getExistingIssues(ProjectEntity projectEntity, List<String> issuesKey, String fields) {
        logger.info("Get modified issues from Jira of project: {}, with issuesKey: {}, with fields: {}", projectEntity, issuesKey, fields);

        String jql = String.format(SEARCH_WITH_ISSUES_QUERIES, StringUtils.join(issuesKey, "\",\""));
        return getIssuesFromJira(projectEntity, jql, fields);
    }

    private List<IssueJiraBean> getIssuesFromJira(ProjectEntity projectEntity, String jql, String fields) {
        int startAt = 0;
        List<IssueJiraBean> issueJiraBeans = new ArrayList<>();
        SearchIssuesResultJiraBean searchIssuesResultJiraBean;

        JiraClient jiraClient = (JiraClient) trackerRouter.getTracker(projectEntity);
        try {
            searchIssuesResultJiraBean = jiraClient.searchIssues(jql, EXPAND, fields, String.valueOf(MAX_RESULT), String.valueOf(startAt));
        } catch (FeignException e) {
            throw new ExternalApiCallException(ExceptionConstant.ERROR_API_CALL + projectEntity, e);
        }

        if (searchIssuesResultJiraBean != null) {
            double pages = Math.ceil((searchIssuesResultJiraBean.getTotal()).doubleValue() / (searchIssuesResultJiraBean.getMaxResults()).doubleValue());

            for (int i = 1; i <= pages; i++) {
                if (i > 1) {
                    startAt = (MAX_RESULT * (i - 1));
                    try {
                        searchIssuesResultJiraBean = jiraClient.searchIssues(jql, EXPAND, fields, String.valueOf(MAX_RESULT), String.valueOf(startAt));
                    } catch (FeignException e) {
                        throw new ExternalApiCallException(ExceptionConstant.ERROR_API_CALL + projectEntity, e);
                    }
                }
                issueJiraBeans.addAll(searchIssuesResultJiraBean.getIssues());
            }
        }

        return issueJiraBeans;
    }
}
