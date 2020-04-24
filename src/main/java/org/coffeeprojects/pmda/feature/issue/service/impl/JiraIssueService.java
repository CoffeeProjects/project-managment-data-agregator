package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.issue.*;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.sprint.SprintUtils;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JiraIssueService implements IssueService {

    private static final Logger log = LoggerFactory.getLogger(JiraIssueService.class);

    private static final String SPRINTS_FIELD = "SPRINTS";
    private static final String JIRA_DEFAULT_FIELDS = "key,project,issuetype,priority,summary,status,creator,reporter,assignee," +
            "updated,created,duedate,labels,components,issuelinks,fixversions,resolution";

    private final IssueRepository issueRepository;

    private final IssueMapper issueMapper;

    private final JiraRepository jiraRepository;

    public JiraIssueService(IssueRepository issueRepository,
                            IssueMapper issueMapper,
                            JiraRepository jiraRepository) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional(noRollbackFor = Exception.class)
    @Override
    public void updateLastModifiedIssues(ProjectEntity projectEntity) {
        String projectFields = IssueUtils.getFields(projectEntity, JIRA_DEFAULT_FIELDS);

        List<IssueJiraBean> issueJiraBeans = jiraRepository.getModifiedIssues(projectEntity, projectFields);
        List<IssueEntity> issueEntities = issueJiraBeans.stream().map(issueMapper::toEntity).collect(Collectors.toList());

        issueJiraBeans.forEach(issueJiraBean -> issueEntities.stream()
                .filter(issueEntity -> issueEntity.getId().getClientId().equalsIgnoreCase(issueJiraBean.getId()))
                .forEach(issueEntity -> {
                    issueEntity.setProject(projectEntity);
                    fillIssueCustomFields(issueEntity, projectEntity, issueJiraBean);
                    fillSprints(issueEntity, projectEntity, issueJiraBean);
                }));
        SprintUtils.updateLastSprintsValuesFromIssueEntities(issueEntities);
        TrackerUtils.fillIdsFromIssueEntities(projectEntity, issueEntities);
        IssueUtils.removeDuplicateUsers(issueEntities);

        try {
            this.issueRepository.saveAll(issueEntities);
        } catch (Exception e) {
            log.error("Error during update last modified issues with project {}. More details => {}", projectEntity, e.getMessage());
        }
    }

    @Transactional(noRollbackFor = Exception.class)
    @Override
    public void deleteMissingIssues(ProjectEntity projectEntity) {
        String projectFields = IssueUtils.getFields(projectEntity, JIRA_DEFAULT_FIELDS);

        List<IssueEntity> unresolvedIssueEntities = this.issueRepository.findByProjectAndResolutionDateIsNull(projectEntity);

        if (!unresolvedIssueEntities.isEmpty()) {
            List<IssueJiraBean> issueJiraBeans = jiraRepository.getExistingIssues(projectEntity, IssueUtils.getKeysFromIssueEntities(unresolvedIssueEntities), projectFields);
            List<IssueEntity> issueEntities = issueJiraBeans.stream().map(issueMapper::toEntity).collect(Collectors.toList());
            List<IssueEntity> issueEntitiesDelta = IssueUtils.getIssueEntitiesDelta(unresolvedIssueEntities, issueEntities);

            if (!issueEntitiesDelta.isEmpty()) {
                try {
                    this.issueRepository.deleteAll(issueEntitiesDelta);
                } catch (RuntimeException e) {
                    log.error("Error during delete missing issues {}. More details => {}", issueEntitiesDelta, e.getMessage());
                }
            }

        }
    }

    private void fillSprints(IssueEntity issueEntity, ProjectEntity projectEntity, IssueJiraBean issueJiraBean) {
        ProjectCustomField projectCustomField = getProjectCustomField(projectEntity, SPRINTS_FIELD);
        List<String> sprints = (ArrayList) getIssueCustomValue(issueJiraBean, projectCustomField);
        SprintUtils.toEntity(sprints, issueEntity);
    }

    private void fillIssueCustomFields(IssueEntity issueEntity, ProjectEntity projectEntity, IssueJiraBean issueJiraBean) {
        Set<IssueCustomField> customFields = new HashSet<>();

        if (projectEntity != null && projectEntity.getProjectCustomFields() != null) {
            projectEntity.getProjectCustomFields().stream()
                    .filter(projectCustomField -> !SPRINTS_FIELD.equalsIgnoreCase(projectCustomField.getLocalName()))
                    .forEach(projectCustomField -> {
                        Object issueCustomFieldValue = getIssueCustomValue(issueJiraBean, projectCustomField);

                        if (issueCustomFieldValue != null) {
                            IssueCustomField issueCustomField = new IssueCustomField();

                            issueCustomField.setId(new CompositeIdBaseEntity()
                                    .setClientId(issueEntity.getId().getClientId() + "_" + projectCustomField.getLocalName())
                                    .setTrackerType(projectEntity.getId().getTrackerType())
                                    .setTrackerLocalId(projectEntity.getId().getTrackerLocalId()));

                            issueCustomField.setProjectCustomFields(projectCustomField).setValue(issueCustomFieldValue.toString());

                            customFields.add(issueCustomField);
                        }
                    });

            issueEntity.setIssueCustomFields(customFields);
        }
    }

    private ProjectCustomField getProjectCustomField(ProjectEntity projectEntity, String field) {
        if (projectEntity != null && projectEntity.getProjectCustomFields() != null) {
            return projectEntity.getProjectCustomFields().stream()
                    .filter(p -> field.equalsIgnoreCase(p.getLocalName()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
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
