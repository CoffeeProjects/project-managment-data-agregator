package org.coffeeprojects.pmda.feature.user.service.impl;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RedmineUserService implements UserService {

    private static final Logger log = LoggerFactory.getLogger(RedmineUserService.class);

    @Transactional
    public void update(ProjectEntity projectEntity) {
        log.debug("Redmine - update user");
    }
}
