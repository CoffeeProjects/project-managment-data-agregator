package org.coffeeprojects.pmda.project;

import org.coffeeprojects.pmda.tracker.jira.proxy.JiraProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectService {

    @Autowired
    private JiraProxy jiraProxy;

    @Transactional
    public ProjectEntity getProjectByKey(String key) {
        return jiraProxy.getProjectByKey(key);
    }
}