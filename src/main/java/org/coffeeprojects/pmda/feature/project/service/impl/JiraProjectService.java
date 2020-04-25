package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.exception.CriticalDataException;
import org.coffeeprojects.pmda.exception.ExceptionConstant;
import org.coffeeprojects.pmda.exception.InvalidDataException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@Service
public class JiraProjectService implements ProjectService {

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

    public ProjectEntity getProjectById(CompositeIdBaseEntity id) {
        return this.projectRepository.findById(id)
                .orElse(null);
    }

    @Transactional(noRollbackFor = InvalidDataException.class)
    public void updateProject(ProjectEntity projectEntity) {
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
    public void updateLastCheckProject(ProjectEntity projectEntity) {
        projectEntity.setLastCheck(Instant.now(clock));

        try {
            this.projectRepository.save(projectEntity);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException(ExceptionConstant.ERROR_PERSISTENCE + e.getMessage(), e);
        }
    }

    @Transactional(noRollbackFor = InvalidDataException.class)
    public void deactivateProject(TrackerParametersBean tracker) throws CriticalDataException {
        ProjectEntity projectEntity = this.initializeProject(tracker, true);
        projectEntity.setActive(Boolean.FALSE);
        try {
            this.projectRepository.save(projectEntity);
        } catch (Exception e) {
            throw new CriticalDataException("Error during deactivation of this local project ID : " + tracker.getLocalId() + " More Details => " + e.getMessage(), e);
        }
    }

    @Transactional(noRollbackFor = InvalidDataException.class)
    public ProjectEntity initializeProject(TrackerParametersBean tracker, boolean forceDeactivate) {
        ProjectEntity projectEntity = null;

        if (tracker != null) {
            projectEntity = this.getProjectById(new CompositeIdBaseEntity()
                    .setTrackerType(tracker.getType())
                    .setTrackerLocalId(tracker.getLocalId())
                    .setClientId(tracker.getClientId()));

            if (projectEntity == null) {
                projectEntity = ((ProjectEntity) new ProjectEntity().setId(new CompositeIdBaseEntity()
                        .setTrackerType(tracker.getType())
                        .setTrackerLocalId(tracker.getLocalId())
                        .setClientId(tracker.getClientId())))
                        .setActive(Boolean.TRUE);
            }

            if (Boolean.TRUE.equals(projectEntity.isActive()) && !forceDeactivate) {
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
