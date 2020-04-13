package org.coffeeprojects.pmda.feature.project.service.impl;

import feign.FeignException;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectJiraBean;
import org.coffeeprojects.pmda.feature.project.ProjectMapper;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class JiraProjectService implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(JiraProjectService.class);

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

    @Transactional
    public void updateProject(ProjectEntity projectEntity) {
        if (TrackerTypeEnum.JIRA.equals(projectEntity.getId().getTrackerType())) {

            try {
                ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(projectEntity);
                ProjectEntity projectEntityFromTracker = projectMapper.toEntity(projectJiraBean);

                projectEntity.setKey(projectEntityFromTracker.getKey());
                projectEntity.setName(projectEntityFromTracker.getName());
                projectEntity.setName(projectEntityFromTracker.getName());

                this.projectRepository.save(projectEntity);

            } catch (FeignException e) {
                log.error("Problem when calling the remote API with this project {}. Please check the configuration file and reactivate it in the database", projectEntity);
                projectEntity.setActive(Boolean.FALSE);
                this.projectRepository.save(projectEntity);
            }
        }
    }

    @Transactional
    public void updateLastCheckProject(ProjectEntity projectEntity) {
        projectEntity.setLastCheck((new Date()).toInstant());
        this.projectRepository.save(projectEntity);
    }

    @Transactional
    public ProjectEntity initializeProject(TrackerParametersBean tracker) {
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

            if (Boolean.TRUE.equals(projectEntity.isActive())) {
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
