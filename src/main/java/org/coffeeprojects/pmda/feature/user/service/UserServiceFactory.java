package org.coffeeprojects.pmda.feature.user.service;

import org.coffeeprojects.pmda.feature.user.service.impl.JiraUserService;
import org.coffeeprojects.pmda.feature.user.service.impl.MantisUserService;
import org.coffeeprojects.pmda.feature.user.service.impl.RedmineUserService;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFactory {

    private final JiraUserService jiraUserService;

    private final MantisUserService mantisUserService;

    private final RedmineUserService redmineUserService;

    public UserServiceFactory(JiraUserService jiraUserService, MantisUserService mantisUserService, RedmineUserService redmineUserService) {
        this.jiraUserService = jiraUserService;
        this.mantisUserService = mantisUserService;
        this.redmineUserService = redmineUserService;
    }

    public UserService getService(TrackerTypeEnum trackerTypeEnum) {
        if (trackerTypeEnum != null) {
            switch (trackerTypeEnum) {
                case JIRA:
                    return jiraUserService;
                case MANTIS:
                    return mantisUserService;
                case REDMINE:
                    return redmineUserService;
            }
        }
        return null;
    }
}
