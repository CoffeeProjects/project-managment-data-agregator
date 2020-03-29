package org.coffeeprojects.pmda.tracker.jira.proxy;

import feign.Param;
import feign.RequestLine;
import org.coffeeprojects.pmda.issue.SearchIssuesResultJiraBean;
import org.coffeeprojects.pmda.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.jira.JiraConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "jira-api", url = "${tracker.jira.url}", configuration = JiraConfiguration.class)
public interface JiraProxy {

    @RequestLine("GET project/{projectKey}")
    ProjectEntity getById(@Param("projectKey") String projectKey);

    @RequestLine("GET search?query={jql}&expand={expand}&fields={fields}")
    SearchIssuesResultJiraBean searchIssues(@Param("jql") String query,
                                            @Param("expand") String expand,
                                            @Param("fields") String fields);

}
