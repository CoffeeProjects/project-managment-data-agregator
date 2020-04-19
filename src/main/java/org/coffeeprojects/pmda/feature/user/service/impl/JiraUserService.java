package org.coffeeprojects.pmda.feature.user.service.impl;

import feign.FeignException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectRepository;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.feature.user.UserMapper;
import org.coffeeprojects.pmda.feature.user.UserRepository;
import org.coffeeprojects.pmda.feature.user.service.UserService;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraCallApiException;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JiraUserService implements UserService {

    private static final Logger log = LoggerFactory.getLogger(JiraUserService.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ProjectRepository projectRepository;

    private final JiraRepository jiraRepository;

    public JiraUserService(UserRepository userRepository,
                           UserMapper userMapper,
                           ProjectRepository projectRepository,
                           JiraRepository jiraRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectRepository = projectRepository;
        this.jiraRepository = jiraRepository;
    }

    @Transactional
    public void update(ProjectEntity projectEntity) {
        try {
            UserJiraBean userJiraBean = jiraRepository.getUserDetails(projectEntity);
            UserEntity userEntity = userMapper.toEntity(userJiraBean);
            TrackerUtils.fillIdsFromUserEntity(projectEntity, userEntity);

            this.userRepository.save(userEntity);
        } catch (JiraCallApiException | FeignException e) {
            log.error("Problem when calling the remote API with this project {}. Please check the configuration file and reactivate it in the database", projectEntity);
            this.projectRepository.save(projectEntity);
        }
    }
}
