package org.coffeeprojects.pmda.tracker.jira;

import feign.Param;
import feign.RequestLine;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;

public interface JiraClient {

    @RequestLine("GET project/{projectKey}")
    ProjectJiraBean getProjectByKey(@Param("projectKey") String key);

    @RequestLine("GET search?query={jql}&expand={expand}&fields={fields}&maxResults={maxResults}&startAt={startAt}")
    SearchIssuesResultJiraBean searchIssues(@Param("jql") String query,
                                            @Param("expand") String expand,
                                            @Param("fields") String fields,
                                            @Param("maxResults") String maxResults,
                                            @Param("startAt") String startAt);
}