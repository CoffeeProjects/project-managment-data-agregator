package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.feature.project.ProjectEnum;
import org.coffeeprojects.pmda.feature.project.service.impl.JiraProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.MantisProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.RedmineProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceFactory {

    private final JiraProjectService jiraProjectService;

    private final MantisProjectService mantisProjectService;

    private final RedmineProjectService redmineProjectService;

    public ProjectServiceFactory(JiraProjectService jiraProjectService, MantisProjectService mantisProjectService, RedmineProjectService redmineProjectService) {
        this.jiraProjectService = jiraProjectService;
        this.mantisProjectService = mantisProjectService;
        this.redmineProjectService = redmineProjectService;
    }

    public ProjectService getService(ProjectEnum projectEnum) {
        if (projectEnum != null) {
            if (ProjectEnum.JIRA.equals(projectEnum)) {
                return jiraProjectService;
            }
            else if (ProjectEnum.MANTIS.equals(projectEnum)) {
                return mantisProjectService;
            }
            else if (ProjectEnum.REDMINE.equals(projectEnum)) {
                return redmineProjectService;
            }
        }
        return null;
    }
}