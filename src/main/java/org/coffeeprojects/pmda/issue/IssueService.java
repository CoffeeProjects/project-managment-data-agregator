package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class IssueService {

    private final JiraRepository jiraRepository;

    public IssueService(JiraRepository jiraRepository) {
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    public void updateIssues(String projectName, Instant fromDate, String expand, String fields) {
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectName, fromDate, expand, fields);
        // TODO : mapper issueJiraBeans en IssueEntity puis mettre à jour en base
    }
}
