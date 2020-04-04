package org.coffeeprojects.pmda.tracker.jira.proxy;

import feign.Param;
import feign.RequestLine;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.tracker.jira.JiraConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "jira-api", url = "${tracker.jira.1.url}", configuration = JiraConfiguration.class)
public interface JiraProxy {

    @RequestLine("GET project/{projectKey}")
    ProjectJiraBean getProjectByKey(@Param("projectKey") String key);

    @RequestLine("GET search?query={jql}&expand={expand}&fields={fields}&maxResults={maxResults}&startAt={startAt}")
    SearchIssuesResultJiraBean searchIssues(@Param("jql") String query,
                                            @Param("expand") String expand,
                                            @Param("fields") String fields,
                                            @Param("maxResults") String maxResults,
                                            @Param("startAt") String startAt);
}
