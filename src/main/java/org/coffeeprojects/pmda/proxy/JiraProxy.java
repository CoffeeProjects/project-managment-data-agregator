package org.coffeeprojects.pmda.proxy;

import feign.Param;
import feign.RequestLine;
import org.coffeeprojects.pmda.JiraConfiguration;
import org.coffeeprojects.pmda.domain.ProjectEntity;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "jira-api", url = "https://herubel.atlassian.net/rest/api/3/", configuration = JiraConfiguration.class)
public interface JiraProxy {

    @RequestLine("GET project/{projectKey}")
    public ProjectEntity getById(@Param("projectKey") String projectKey);

}