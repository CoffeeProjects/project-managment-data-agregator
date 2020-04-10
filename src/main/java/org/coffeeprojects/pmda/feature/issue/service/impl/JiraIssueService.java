package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.feature.issue.IssueCustomField;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.issue.IssueMapper;
import org.coffeeprojects.pmda.feature.issue.IssueRepository;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.feature.sprint.SprintUtils;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JiraIssueService implements IssueService {

    private static final Logger log = LoggerFactory.getLogger(JiraIssueService.class);

    private static final String SPRINTS_FIELD = "SPRINTS";
    private static final String RESOLUTION_DATE_FIELD = "RESOLUTION_DATE";

    private static final String JIRA_FIELDS = "key,project,issuetype,priority,summary,status,creator,reporter,assignee," +
            "updated,created,duedate,labels,components,issuelinks,fixversions,resolution,customfield_10020";

    private final ProjectRepository projectRepository;

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public JiraIssueService(ProjectRepository projectRepository,
                            IssueRepository issueRepository,
                            IssueMapper issueMapper,
                            JiraRepository jiraRepository) {
        this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    @Override
    public void updateLastModifiedIssues(ProjectEntity projectEntity) {
        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity, JIRA_FIELDS);
        List<IssueEntity> issueEntities = issueJiraBeans.stream().map(issueMapper::toEntity).collect(Collectors.toList());

        issueJiraBeans.forEach(issueJiraBean -> {
            issueEntities.stream()
                    .filter(issueEntity -> issueEntity.getId().getClientId().equalsIgnoreCase(issueJiraBean.getId()))
                    .forEach(issueEntity -> {
                        fillResolutionDate(issueEntity, projectEntity, issueJiraBean);
                        fillSprints(issueEntity, projectEntity, issueJiraBean);
                    });
        });
        TrackerUtils.fillIdsFromIssueEntities(projectEntity, issueEntities);

        try {
            this.issueRepository.saveAll(issueEntities);
        } catch (Exception e) {
            log.error("Error during update last modified issues");
        }
    }

    private void fillResolutionDate(IssueEntity issueEntity, ProjectEntity projectEntity, IssueJiraBean issueJiraBean) {
        ProjectCustomField projectCustomField = getProjectCustomField(projectEntity, RESOLUTION_DATE_FIELD);
        String resolutionDate = (String) getIssueCustomValue(issueJiraBean, projectCustomField);

        issueEntity.setResolutionDate(TrackerUtils.getInstantFromTimezone(resolutionDate));
    }

    private void fillSprints(IssueEntity issueEntity, ProjectEntity projectEntity, IssueJiraBean issueJiraBean) {
        ProjectCustomField projectCustomField = getProjectCustomField(projectEntity, SPRINTS_FIELD);
        List<String> sprints = (ArrayList) getIssueCustomValue(issueJiraBean, projectCustomField);

        SprintUtils.toEntity(sprints, issueEntity);
    }

    private void fillIssueCustomFields(IssueEntity issueEntity, ProjectEntity projectEntity, IssueJiraBean issueJiraBean) {
        Set<IssueCustomField> customFields = new HashSet();

        projectEntity.getProjectCustomFields().stream()
                .filter(projectCustomField -> !SPRINTS_FIELD.equalsIgnoreCase(projectCustomField.getLocalName()))
                .filter(projectCustomField -> !RESOLUTION_DATE_FIELD.equalsIgnoreCase(projectCustomField.getLocalName()))
                .forEach(projectCustomField -> {
                    String issueCustomFieldValue = (String) getIssueCustomValue(issueJiraBean, projectCustomField);

                if (StringUtils.isNotEmpty(issueCustomFieldValue)) {
                    customFields.add(new IssueCustomField().setProjectCustomFields(projectCustomField).setValue(issueCustomFieldValue));
                }
        });

        issueEntity.setIssueCustomFields(customFields);
    }

    private ProjectCustomField getProjectCustomField(ProjectEntity projectEntity, String field) {
        return projectEntity.getProjectCustomFields().stream()
                .filter(p -> field.equalsIgnoreCase(p.getLocalName()))
                .findFirst()
                .orElse(null);
    }

    private Object getIssueCustomValue(IssueJiraBean issueJiraBean, ProjectCustomField projectCustomField) {
        if (projectCustomField != null && projectCustomField.getClientName() != null) {
            return issueJiraBean.getFields().getCustomFields().entrySet().stream()
                    .filter(map -> projectCustomField.getClientName().equals(map.getKey()))
                    .filter(map -> map.getValue() != null)
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElse(null);

        }
        return null;
    }
}
