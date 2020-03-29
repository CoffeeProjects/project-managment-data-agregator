package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public IssueService(IssueRepository issueRepository, IssueMapper issueMapper, JiraRepository jiraRepository) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    public void updateLastModifiedIssues(String projectName, Instant fromDate, String expand, String fields) {
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectName, fromDate, expand, fields);
        this.issueRepository.saveAll(issueMapper.toEntities(issueJiraBeans));
        // TODO : à tester
    }
}
