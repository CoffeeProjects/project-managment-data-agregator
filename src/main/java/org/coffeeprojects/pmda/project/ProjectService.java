package org.coffeeprojects.pmda.project;

import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

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
    public void updateProjectByKey(String key) {
        ProjectJiraBean projectJiraBean = jiraRepository.getProjectDetails(key);
        ProjectEntity projectEntity = projectMapper.toEntity(projectJiraBean);

        Date currentDateTime = new Date();
        projectEntity.setLastCheck(currentDateTime);
        projectEntity.setUpdatedAt(currentDateTime);

        this.projectRepository.saveAndFlush(projectEntity);
    }
}