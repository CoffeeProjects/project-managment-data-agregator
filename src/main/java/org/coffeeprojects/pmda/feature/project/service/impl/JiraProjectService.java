package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.*;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class JiraProjectService implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private final JiraRepository jiraRepository;

    public JiraProjectService(ProjectRepository projectRepository,
                              ProjectMapper projectMapper,
                              JiraRepository jiraRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.jiraRepository = jiraRepository;
    }

    public ProjectEntity getProjectById(CompositeIdBaseEntity id) {
        return this.projectRepository.findById(id)
                .orElse(null);
    }

    @Transactional(noRollbackFor = Exception.class)
    public void updateProject(ProjectEntity projectEntity) {
        if (TrackerTypeEnum.JIRA.equals(projectEntity.getId().getTrackerType())) {
            ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(projectEntity);
            ProjectEntity projectEntityFromTracker = projectMapper.toEntity(projectJiraBean);
            TrackerUtils.fillIdsFromUserEntity(projectEntity, projectEntityFromTracker.getAdministrator());

            projectEntity.setKey(projectEntityFromTracker.getKey());
            projectEntity.setName(projectEntityFromTracker.getName());
            projectEntity.setAdministrator(projectEntityFromTracker.getAdministrator());

            this.projectRepository.save(projectEntity);
        }
    }

    @Transactional(noRollbackFor = Exception.class)
    public void updateLastCheckProject(ProjectEntity projectEntity) {
        projectEntity.setLastCheck((new Date()).toInstant());
        this.projectRepository.save(projectEntity);
    }

    @Transactional(noRollbackFor = Exception.class)
    public void deactivateProject(TrackerParametersBean tracker) throws ProjectServiceException {
        ProjectEntity projectEntity = this.initializeProject(tracker, true);
        projectEntity.setActive(Boolean.FALSE);
        try {
            this.projectRepository.save(projectEntity);
        } catch (Exception e) {
            throw new ProjectServiceException("Error during deactivation of this local project ID : " + tracker.getLocalId() + " More Details => " + e.getMessage());
        }
    }

    @Transactional(noRollbackFor = Exception.class)
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
