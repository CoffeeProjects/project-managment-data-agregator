package org.coffeeprojects.pmda.feature.user.service.impl;

import org.coffeeprojects.pmda.exception.InvalidDataException;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.feature.user.UserMapper;
import org.coffeeprojects.pmda.feature.user.UserRepository;
import org.coffeeprojects.pmda.feature.user.service.UserService;
import org.coffeeprojects.pmda.tracker.TrackerUtils;
import org.coffeeprojects.pmda.tracker.jira.JiraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JiraUserService implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JiraRepository jiraRepository;

    public JiraUserService(UserRepository userRepository,
                           UserMapper userMapper,
                           JiraRepository jiraRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jiraRepository = jiraRepository;
    }

    @Transactional(noRollbackFor = InvalidDataException.class)
    public void update(ProjectEntity projectEntity) {
        UserJiraBean userJiraBean = jiraRepository.getUserDetails(projectEntity);
        UserEntity userEntity = userMapper.toEntity(userJiraBean);
        TrackerUtils.fillIdsFromUserEntity(projectEntity, userEntity);

        try {
            this.userRepository.save(userEntity);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Problem during persistence" + e.getMessage());
        }
    }
}
