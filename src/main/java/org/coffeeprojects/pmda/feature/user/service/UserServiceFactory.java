package org.coffeeprojects.pmda.feature.user.service;

import org.coffeeprojects.pmda.feature.user.service.impl.JiraUserService;
import org.coffeeprojects.pmda.feature.user.service.impl.MantisUserService;
import org.coffeeprojects.pmda.feature.user.service.impl.RedmineUserService;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class UserServiceFactory {

    private final Map<TrackerType, UserService> trackerUserServiceMap = new EnumMap<>(TrackerType.class);

    public UserServiceFactory(JiraUserService jiraUserService, MantisUserService mantisUserService, RedmineUserService redmineUserService) {
        trackerUserServiceMap.put(TrackerType.JIRA, jiraUserService);
        trackerUserServiceMap.put(TrackerType.MANTIS, mantisUserService);
        trackerUserServiceMap.put(TrackerType.REDMINE, redmineUserService);
    }

    /**
     * Get user service by tracker type
     *
     * @param trackerType The tracker type
     * @return The service or null
     */
    public UserService getService(TrackerType trackerType) {
        return trackerUserServiceMap.get(trackerType);
    }
}
