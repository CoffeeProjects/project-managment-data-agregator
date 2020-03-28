package org.coffeeprojects.pmda.service.impl;

import org.coffeeprojects.pmda.domain.ProjectEntity;
import org.coffeeprojects.pmda.proxy.JiraProxy;
import org.coffeeprojects.pmda.service.ProjectsService;
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