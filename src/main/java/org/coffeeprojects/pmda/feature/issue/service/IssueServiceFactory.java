package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.issue.service.impl.JiraIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.MantisIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.RedmineIssueService;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class IssueServiceFactory {

    private final Map<TrackerType, IssueService> trackerIssueServiceMap = new EnumMap<>(TrackerType.class);

    public IssueServiceFactory(JiraIssueService jiraIssueService, MantisIssueService mantisIssueService, RedmineIssueService redmineIssueService) {
        trackerIssueServiceMap.put(TrackerType.JIRA, jiraIssueService);
        trackerIssueServiceMap.put(TrackerType.MANTIS, mantisIssueService);
        trackerIssueServiceMap.put(TrackerType.REDMINE, redmineIssueService);
    }

    /**
     * Get issue service by tracker type
     *
     * @param trackerType The tracker type
     * @return The service or null
     */
    public IssueService getService(TrackerType trackerType) {
        return trackerIssueServiceMap.get(trackerType);
    }
}
