package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.ExceptionConstant;
import org.coffeeprojects.pmda.exception.InvalidDataException;
import org.coffeeprojects.pmda.feature.issue.*;
import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.sprint.SprintUtils;
import org.coffeeprojects.pmda.tracker.ExternalApiCallException;
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

    protected static final String JIRA_DEFAULT_FIELDS = "key,project,issuetype,priority,summary,status,creator,reporter,assignee," +
            "updated,created,duedate,labels,fixVersions,timetracking,components,issuelinks,fixversions,resolution,resolutiondate";
    private static final Logger logger = LoggerFactory.getLogger(JiraIssueService.class);
    private static final String SPRINTS_FIELD = "SPRINTS";
    public static final String ID = "id";
    public static final String VALUE = "value";

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

    @Transactional(noRollbackFor = {ExternalApiCallException.class, InvalidDataException.class})
    @Override
    public void updateLastModifiedIssues(ProjectEntity projectEntity) {
        logger.info("Update last Jira modified issues of project: {}", projectEntity);

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
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException(ExceptionConstant.ERROR_UPDATE_ISSUES + projectEntity + ExceptionConstant.ERROR_MORE_DETAILS + e.getMessage(), e);
        }
    }

    @Transactional(noRollbackFor = {ExternalApiCallException.class, InvalidDataException.class})
    @Override
    public void deleteMissingIssues(ProjectEntity projectEntity) {
        logger.info("Delete Jira missing issues of project: {}", projectEntity);

        String projectFields = IssueUtils.getFields(projectEntity, JIRA_DEFAULT_FIELDS);

        List<IssueEntity> unresolvedIssueEntities = this.issueRepository.findByProjectAndResolutionDateIsNull(projectEntity);

        if (!unresolvedIssueEntities.isEmpty()) {
            List<IssueJiraBean> issueJiraBeans = jiraRepository.getExistingIssues(projectEntity, IssueUtils.getKeysFromIssueEntities(unresolvedIssueEntities), projectFields);
            List<IssueEntity> issueEntities = issueJiraBeans.stream().map(issueMapper::toEntity).collect(Collectors.toList());
            List<IssueEntity> issueEntitiesDelta = IssueUtils.getIssueEntitiesDelta(unresolvedIssueEntities, issueEntities);

            if (!issueEntitiesDelta.isEmpty()) {
                logger.info("Issues to delete: {}", issueEntitiesDelta.stream().map(IssueEntity::getId).collect(Collectors.toList()));
                try {
                    this.issueRepository.deleteAll(issueEntitiesDelta);
                } catch (IllegalArgumentException e) {
                    throw new InvalidDataException(ExceptionConstant.ERROR_DELETE_ISSUES + issueEntitiesDelta + ExceptionConstant.ERROR_MORE_DETAILS + e.getMessage(), e);
                }
            }
        }
    }

    private void fillSprints(IssueEntity issueEntity, ProjectEntity projectEntity, IssueJiraBean issueJiraBean) {
        ProjectCustomField projectCustomField = getProjectCustomField(projectEntity, SPRINTS_FIELD);
        List<LinkedHashMap<Object, Object>> sprints = (List<LinkedHashMap<Object, Object>>) getIssueCustomValue(issueJiraBean, projectCustomField);
        SprintUtils.toEntity(sprints, issueEntity);
    }

    private void fillIssueCustomFields(IssueEntity issueEntity, ProjectEntity projectEntity, IssueJiraBean issueJiraBean) {
        Set<IssueCustomField> customFields = new HashSet<>();

        Optional.ofNullable(projectEntity)
                .map(ProjectEntity::getProjectCustomFields)
                .orElse(new HashSet<>())
                .stream()
                .filter(projectCustomField -> !SPRINTS_FIELD.equalsIgnoreCase(projectCustomField.getLocalName()))
                .forEach(projectCustomField -> {
                    Object issueCustomFieldValue = getIssueCustomValue(issueJiraBean, projectCustomField);

                    if (issueCustomFieldValue != null) {
                        if (issueCustomFieldValue instanceof ArrayList) {
                            fillListOfCustomField(issueEntity, projectEntity, customFields, projectCustomField, (ArrayList) issueCustomFieldValue);
                        } else if (issueCustomFieldValue instanceof HashMap) {
                            fillCustomField(issueEntity, projectEntity, customFields, projectCustomField, (HashMap) issueCustomFieldValue);
                        } else {
                            fillDefaultCustomField(issueEntity, projectEntity, customFields, projectCustomField, issueCustomFieldValue);
                        }
                    }
                    issueEntity.setIssueCustomFields(customFields);
                });
    }

    private void fillListOfCustomField(IssueEntity issueEntity, ProjectEntity projectEntity, Set<IssueCustomField> customFields, ProjectCustomField projectCustomField, ArrayList<Object> issueCustomFieldValues) {
        issueCustomFieldValues.forEach(i -> {
            if (i instanceof HashMap) {
                fillCustomField(issueEntity, projectEntity, customFields, projectCustomField, (HashMap) i);
            } else {
                fillDefaultCustomField(issueEntity, projectEntity, customFields, projectCustomField, i);
            }
        });
    }

    private void fillCustomField(IssueEntity issueEntity, ProjectEntity projectEntity, Set<IssueCustomField> customFields, ProjectCustomField projectCustomField, HashMap<Object, Object> issueCustomFieldValue) {
        IssueCustomField issueCustomField = new IssueCustomField();
        issueCustomField.setId(new CompositeIdBaseEntity()
                .setClientId(issueEntity.getId().getClientId() + "_" + issueCustomFieldValue.get(ID) + "_" + projectCustomField.getLocalName())
                .setTrackerType(projectEntity.getId().getTrackerType())
                .setTrackerLocalId(projectEntity.getId().getTrackerLocalId()));

        issueCustomField.setValue(Optional.ofNullable(issueCustomFieldValue
                .get(VALUE))
                .map(Object::toString)
                .orElse(""));
        customFields.add(issueCustomField);
    }

    private void fillDefaultCustomField(IssueEntity issueEntity, ProjectEntity projectEntity, Set<IssueCustomField> customFields, ProjectCustomField projectCustomField, Object issueCustomFieldValue) {
        IssueCustomField issueCustomField = new IssueCustomField();
        issueCustomField.setId(new CompositeIdBaseEntity()
                .setClientId(issueEntity.getId().getClientId() + "_" + projectCustomField.getLocalName())
                .setTrackerType(projectEntity.getId().getTrackerType())
                .setTrackerLocalId(projectEntity.getId().getTrackerLocalId()));

        issueCustomField.setValue(Optional.ofNullable(issueCustomFieldValue)
                .map(Object::toString)
                .orElse(""));
        customFields.add(issueCustomField);
    }

    private ProjectCustomField getProjectCustomField(ProjectEntity projectEntity, String field) {
        return Optional.ofNullable(projectEntity)
                .map(ProjectEntity::getProjectCustomFields)
                .orElse(Collections.emptySet())
                .stream()
                .filter(p -> p.getLocalName().equalsIgnoreCase(field))
                .findFirst()
                .orElse(null);
    }

    private Object getIssueCustomValue(IssueJiraBean issueJiraBean, ProjectCustomField projectCustomField) {
        String projectCustomFieldClientName = Optional.ofNullable(projectCustomField).orElse(new ProjectCustomField()).getClientName();
        return issueJiraBean.getFields().getCustomFields().entrySet().stream()
                .filter(customFieldEntry -> customFieldEntry.getKey().equals(projectCustomFieldClientName))
                .filter(customFieldEntry -> customFieldEntry.getValue() != null)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }
}
