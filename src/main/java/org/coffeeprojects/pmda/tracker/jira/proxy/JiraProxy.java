package org.coffeeprojects.pmda.tracker.jira.proxy;

import feign.Param;
import feign.RequestLine;
import org.coffeeprojects.pmda.tracker.jira.JiraConfiguration;
import org.coffeeprojects.pmda.project.ProjectEntity;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "jira-api", url = "https://herubel.atlassian.net/rest/api/3/", configuration = JiraConfiguration.class)
public interface JiraProxy {

    @RequestLine("GET project/{projectKey}")
    public ProjectEntity getById(@Param("projectKey") String projectKey);

}