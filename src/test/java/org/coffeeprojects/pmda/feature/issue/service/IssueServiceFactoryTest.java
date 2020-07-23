package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.feature.issue.service.impl.JiraIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.MantisIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.RedmineIssueService;
import org.coffeeprojects.pmda.tracker.TrackerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class IssueServiceFactoryTest {

    @Mock
    private JiraIssueService jiraIssueService;
    @Mock
    private MantisIssueService mantisIssueService;
    @Mock
    private RedmineIssueService redmineIssueService;

    private IssueServiceFactory issueServiceFactory;

    @BeforeEach
    void setup() {
        issueServiceFactory = new IssueServiceFactory(jiraIssueService, mantisIssueService, redmineIssueService);
    }

    @Test
    void get_service_should_return_user_service() {
        assertThat(issueServiceFactory.getService(TrackerType.JIRA)).isEqualTo(jiraIssueService);
        assertThat(issueServiceFactory.getService(TrackerType.MANTIS)).isEqualTo(mantisIssueService);
        assertThat(issueServiceFactory.getService(TrackerType.REDMINE)).isEqualTo(redmineIssueService);
        assertThat(issueServiceFactory.getService(null)).isNull();
    }
}
