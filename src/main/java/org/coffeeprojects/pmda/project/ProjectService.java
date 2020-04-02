package org.coffeeprojects.pmda.project;

import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
    public void updateProjectByKey(ProjectEntity projectEntity) {
        ProjectEntity projectEntityFromTracker = null;

        if (ProjectEnum.JIRA.equals(projectEntity.getType())) {
            ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(projectEntity.getKey());
            projectEntityFromTracker = projectMapper.toEntity(projectJiraBean);
        }

        Date currentDateTime = new Date();
        projectEntityFromTracker.setLastCheck(currentDateTime);
        projectEntityFromTracker.setUpdatedAt(currentDateTime);

        this.projectRepository.save(projectEntityFromTracker);
    }

    @Transactional
    public List<ProjectEntity> getAllProjectsFromDatabase() {
        return this.projectRepository.findAll();
    }
}