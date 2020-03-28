package org.coffeeprojects.pmda.project.impl;

import org.coffeeprojects.pmda.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.jira.proxy.JiraProxy;
import org.coffeeprojects.pmda.project.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsServiceIml implements ProjectsService {

    @Autowired
    private JiraProxy jiraProxy;

    public ProjectEntity getById() {
        return jiraProxy.getById("PMDA");
    }

}