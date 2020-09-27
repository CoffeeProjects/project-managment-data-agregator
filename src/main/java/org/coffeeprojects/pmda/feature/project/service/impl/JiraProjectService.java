package org.coffeeprojects.pmda.feature.project.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.CriticalDataException;
import org.coffeeprojects.pmda.exception.ExceptionConstant;
import org.coffeeprojects.pmda.exception.InvalidDataException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.tracker.ExternalApiCallException;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@Service
public class JiraProjectService implements ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(JiraProjectService.class);

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private final JiraRepository jiraRepository;

    private final Clock clock;

    public JiraProjectService(ProjectRepository projectRepository,
                              ProjectMapper projectMapper,
                              JiraRepository jiraRepository,
                              Clock clock) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.jiraRepository = jiraRepository;
        this.clock = clock;
    }

    @Transactional(readOnly = true)
    @Override
    public ProjectEntity getProjectById(CompositeIdBaseEntity id) {
        logger.info("Get Jira project by id: {}", id);
        return this.projectRepository.findById(id)
                .orElse(null);
    }

    @Transactional(noRollbackFor = {ExternalApiCallException.class, InvalidDataException.class})
    @Override
    public void updateProject(ProjectEntity projectEntity) {
        logger.info("Update Jira project: {}", projectEntity);
        ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(projectEntity);
        ProjectEntity projectEntityFromTracker = projectMapper.toEntity(projectJiraBean);
        TrackerUtils.fillIdsFromUserEntity(projectEntity, projectEntityFromTracker.getAdministrator());

        projectEntity.setKey(projectEntityFromTracker.getKey());
        projectEntity.setName(projectEntityFromTracker.getName());
        projectEntity.setAdministrator(projectEntityFromTracker.getAdministrator());

        try {
            this.projectRepository.save(projectEntity);
        } catch (IllegalArgumentException e) {
             throw new InvalidDataException(ExceptionConstant.ERROR_PERSISTENCE + e.getMessage(), e);
        }
    }

    @Transactional(noRollbackFor = InvalidDataException.class)
    @Override
    public void updateLastCheckProject(ProjectEntity projectEntity) {
        logger.info("Update last check of Jira project: {}", projectEntity);
        projectEntity.setLastCheck(Instant.now(clock));

        try {
            this.projectRepository.save(projectEntity);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException(ExceptionConstant.ERROR_PERSISTENCE + e.getMessage(), e);
        }
    }

    @Transactional(noRollbackFor = {ExternalApiCallException.class, InvalidDataException.class})
    @Override
    public void deactivateProjectOnError(TrackerParametersBean tracker, RuntimeException error) throws CriticalDataException {
        logger.info("Deactivate Jira project: {}", tracker);
        ProjectEntity projectEntity = initializeProject(tracker, false, true);

        Integer failureCounter = projectEntity.getFailureCounter() == null ? 0 : projectEntity.getFailureCounter();
        projectEntity.setFailureCounter(failureCounter + 1);
        projectEntity.setLastFailureDate(Instant.now(clock));
        projectEntity.setLastFailureMessage(StringUtils.substring(error.getMessage() + " >> " + ExceptionUtils.getStackTrace(error), 0, 1000));
        projectEntity.setActive(Boolean.FALSE);
        try {
            this.projectRepository.save(projectEntity);
        } catch (Exception e) {
            throw new CriticalDataException("Error during deactivation of this local project ID : " + tracker.getLocalId() + " More Details => " + e.getMessage(), e);
        }
    }

    @Transactional(noRollbackFor = InvalidDataException.class)
    @Override
    public void reactivateProject(ProjectEntity projectEntity) throws CriticalDataException {
        logger.info("Reactivate Jira project: {}", projectEntity);
        projectEntity.setFailureCounter(0);
        projectEntity.setLastFailureDate(null);
        projectEntity.setLastFailureMessage(null);
        projectEntity.setActive(Boolean.TRUE);
        try {
            this.projectRepository.save(projectEntity);
        } catch (Exception e) {
            throw new CriticalDataException("Error during deactivation of this local project ID : " + projectEntity.getId() + " More Details => " + e.getMessage(), e);
        }
    }

    @Transactional(noRollbackFor = {ExternalApiCallException.class, InvalidDataException.class})
    @Override
    public ProjectEntity initializeProject(TrackerParametersBean tracker, boolean forceRetry, boolean forceDeactivate) {
        logger.info("Initialize Jira project: {}, hasDeactivated: {}", tracker, forceDeactivate);
        ProjectEntity projectEntity = null;

        if (tracker != null) {
            projectEntity = this.getProjectById(new CompositeIdBaseEntity()
                    .setTrackerType(tracker.getType())
                    .setTrackerLocalId(tracker.getLocalId())
                    .setClientId(tracker.getClientId()));

            if (projectEntity == null) {
                projectEntity = (new ProjectEntity().setId(new CompositeIdBaseEntity()
                        .setTrackerType(tracker.getType())
                        .setTrackerLocalId(tracker.getLocalId())
                        .setClientId(tracker.getClientId())))
                        .setActive(Boolean.TRUE);
            }

            if (Boolean.TRUE.equals(projectEntity.isActive()) && !forceRetry && !forceDeactivate) {
                // Update project
                this.updateProject(projectEntity);

                projectEntity = this.getProjectById(new CompositeIdBaseEntity()
                        .setTrackerType(tracker.getType())
                        .setTrackerLocalId(tracker.getLocalId())
                        .setClientId(tracker.getClientId()));
            }
        }
        return projectEntity;
    }
}
