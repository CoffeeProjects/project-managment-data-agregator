package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public IssueService(IssueRepository issueRepository,
                        IssueMapper issueMapper,
                        JiraRepository jiraRepository) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    public void updateLastModifiedIssues(String projectName, Instant fromDate, String expand, String fields) {
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectName, fromDate, expand, fields);
        List<IssueEntity> issueEntities = issueJiraBeans.stream().map(issueMapper::toEntity).collect(Collectors.toList());
//        this.issueRepository.saveAll(issueEntities);
        // TODO : à tester
    }
}
