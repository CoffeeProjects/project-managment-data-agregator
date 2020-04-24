package org.coffeeprojects.pmda.feature.user.service.impl;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MantisUserService implements UserService {

    private static final Logger log = LoggerFactory.getLogger(MantisUserService.class);

    @Transactional
    public void update(ProjectEntity projectEntity) {
        log.debug("Mantis - update user");
    }
}
