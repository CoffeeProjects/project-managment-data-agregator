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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JiraUserService implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(JiraUserService.class);

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

    @Override
    @Transactional(noRollbackFor = InvalidDataException.class)
    public void update(ProjectEntity projectEntity) {
        logger.info("Update Jira users of project: {}", projectEntity);
        UserJiraBean userJiraBean = jiraRepository.getUserDetails(projectEntity);
        UserEntity userEntity = userMapper.toEntity(userJiraBean);
        TrackerUtils.fillIdsFromUserEntity(projectEntity, userEntity);

        try {
            this.userRepository.save(userEntity);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Problem during persistence of jira users of project "
                    + projectEntity + ": " + e.getMessage(), e);
        }
    }
}
