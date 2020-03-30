package org.coffeeprojects.pmda.issue;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

//    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public IssueService(IssueRepository issueRepository,
//                        IssueMapper issueMapper,
                        JiraRepository jiraRepository) {
        this.issueRepository = issueRepository;
//        this.issueMapper = issueMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    public void updateLastModifiedIssues(String projectName, Instant fromDate, String expand, String fields) {
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectName, fromDate, expand, fields);

        // TODO : Mapper ces sprints dans SprintJiraBean et les intégrer dans issueEntity
        for (IssueJiraBean issueJiraBean : issueJiraBeans) {
            jiraRepository.getSprintsByIssueJiraBean(issueJiraBean);
        }

//        this.issueRepository.saveAll(issueMapper.toEntities(issueJiraBeans));
        // TODO : à tester
    }
}
