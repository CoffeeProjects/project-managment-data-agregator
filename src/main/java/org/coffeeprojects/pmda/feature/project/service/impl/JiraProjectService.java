package org.coffeeprojects.pmda.feature.project.service.impl;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.*;
import org.coffeeprojects.pmda.feature.project.service.ProjectService;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public void updateProjectByKey(ProjectEntity projectEntity) {
        if (TrackerTypeEnum.JIRA.equals(projectEntity.getId().getTrackerType())) {
            ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(projectEntity);
            ProjectEntity projectEntityFromTracker = projectMapper.toEntity(projectJiraBean);
            projectEntityFromTracker.setId(projectEntity.getId());
            projectEntityFromTracker.setLastCheck((new Date()).toInstant());
            this.projectRepository.save(projectEntityFromTracker);
        }
    }
}
