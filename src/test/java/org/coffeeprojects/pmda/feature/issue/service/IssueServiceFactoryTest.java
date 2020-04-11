package org.coffeeprojects.pmda.feature.issue.service;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueMapper;
import org.coffeeprojects.pmda.feature.issue.IssueRepository;
import org.coffeeprojects.pmda.feature.issue.service.impl.JiraIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.MantisIssueService;
import org.coffeeprojects.pmda.feature.issue.service.impl.RedmineIssueService;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class IssueServiceFactoryTest {

    @Mock
    private IssueRepository issueRepository;

    @Mock
    private IssueMapper issueMapper;

    @Mock
    private JiraRepository jiraRepository;

    @Test
    public void test_get_issue_service_with_project_entity_null() {
        IssueServiceFactory issueServiceFactory = new IssueServiceFactory(null, null, null);
        issueServiceFactory.getService(null);

    }

    @Test
    public void test_get_issue_service_project_id_null() {
        ProjectEntity projectEntity = new ProjectEntity();

        IssueServiceFactory issueServiceFactory = new IssueServiceFactory(null, null, null);
        assertThat(issueServiceFactory.getService(projectEntity)).isNull();
    }

    @Test
    public void test_get_issue_service_project_type_null() {
        ProjectEntity projectEntity = new ProjectEntity();

        IssueServiceFactory issueServiceFactory = new IssueServiceFactory(null, null, null);
        assertThat(issueServiceFactory.getService(projectEntity)).isNull();
    }

    @Test
    public void test_get_issue_service_tracker_type_null() {
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity();
        ProjectEntity projectEntity = (ProjectEntity) new ProjectEntity().setId(projectId);

        IssueServiceFactory issueServiceFactory = new IssueServiceFactory(null, null, null);
        assertThat(issueServiceFactory.getService(projectEntity)).isNull();
    }

    @Test
    public void test_get_issue_service_jira_project() {
        // Project
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = (ProjectEntity) new ProjectEntity().setId(projectId);
        // Issue service
        JiraIssueService jiraIssueService = new JiraIssueService(issueRepository, issueMapper, jiraRepository);

        IssueServiceFactory issueServiceFactory = new IssueServiceFactory(jiraIssueService, null, null);
        assertThat(issueServiceFactory.getService(projectEntity)).isInstanceOf(JiraIssueService.class);
    }

    @Test
    public void test_get_issue_service_mantis_project() {
        // Project
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.MANTIS);
        ProjectEntity projectEntity = (ProjectEntity) new ProjectEntity().setId(projectId);
        // Issue service
        MantisIssueService mantisIssueService = new MantisIssueService(issueRepository, issueMapper, jiraRepository);

        IssueServiceFactory issueServiceFactory = new IssueServiceFactory(null, mantisIssueService, null);
        assertThat(issueServiceFactory.getService(projectEntity)).isInstanceOf(MantisIssueService.class);
    }

    @Test
    public void test_get_issue_service_redmine_project() {
        // Project
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.REDMINE);
        ProjectEntity projectEntity = (ProjectEntity) new ProjectEntity().setId(projectId);
        // Issue service
        RedmineIssueService redmineIssueService = new RedmineIssueService(issueRepository, issueMapper, jiraRepository);

        IssueServiceFactory issueServiceFactory = new IssueServiceFactory(null, null, redmineIssueService);
        assertThat(issueServiceFactory.getService(projectEntity)).isInstanceOf(RedmineIssueService.class);
    }
}
