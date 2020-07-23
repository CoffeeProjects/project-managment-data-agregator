package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.feature.project.service.impl.JiraProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.MantisProjectService;
import org.coffeeprojects.pmda.feature.project.service.impl.RedmineProjectService;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class ProjectServiceFactory {

    private final Map<TrackerType, ProjectService> trackerProjectServiceMap = new EnumMap<>(TrackerType.class);

    public ProjectServiceFactory(JiraProjectService jiraProjectService, MantisProjectService mantisProjectService, RedmineProjectService redmineProjectService) {
        trackerProjectServiceMap.put(TrackerType.JIRA, jiraProjectService);
        trackerProjectServiceMap.put(TrackerType.MANTIS, mantisProjectService);
        trackerProjectServiceMap.put(TrackerType.REDMINE, redmineProjectService);
    }

    /**
     * Get project service by tracker type
     *
     * @param trackerType The tracker type
     * @return The service or null
     */
    public ProjectService getService(TrackerType trackerType) {
        return trackerProjectServiceMap.get(trackerType);
    }
}
