package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.project.ProjectEntity;
import org.coffeeprojects.pmda.project.ProjectRepository;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

    private final ProjectRepository projectRepository;

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public IssueService(ProjectRepository projectRepository,
                        IssueRepository issueRepository,
                        IssueMapper issueMapper,
                        JiraRepository jiraRepository) {
        this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    public void updateLastModifiedIssues(ProjectEntity projectEntity, Instant fromDate, String fields) {
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity.getKey(), fromDate, fields);
        List<IssueEntity> issueEntities = issueJiraBeans.stream().map(issueMapper::toEntity).collect(Collectors.toList());
        try {
            this.issueRepository.saveAll(issueEntities);

            projectEntity.setLastCheck(new Date());
            this.projectRepository.save(projectEntity);
        } catch (Exception e) {

        }
        // TODO : à tester
    }
}
