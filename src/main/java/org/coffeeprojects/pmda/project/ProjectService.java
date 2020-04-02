package org.coffeeprojects.pmda.project;

import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    private final JiraRepository jiraRepository;

    public ProjectService(ProjectRepository projectRepository,
                          ProjectMapper projectMapper,
                        JiraRepository jiraRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    public ProjectEntity getProjectByKey(String key) {
        ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(key);
        ProjectEntity projectEntity = projectMapper.toEntity(projectJiraBean);
        this.projectRepository.save(projectEntity);
        return projectEntity;
    }
}