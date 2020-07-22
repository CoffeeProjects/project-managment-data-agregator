package org.coffeeprojects.pmda.feature.project.service;

import org.coffeeprojects.pmda.exception.CriticalDataException;
import org.coffeeprojects.pmda.feature.issue.service.IssueService;
import org.coffeeprojects.pmda.feature.issue.service.IssueServiceFactory;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.service.UserService;
import org.coffeeprojects.pmda.feature.user.service.UserServiceFactory;
import org.coffeeprojects.pmda.tracker.TrackerParametersBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectUpdateService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectUpdateService.class);

    private final ProjectServiceFactory projectServiceFactory;

    private final UserServiceFactory userServiceFactory;

    private final IssueServiceFactory issueServiceFactory;

    @Value("${scheduler.trigger.project-max-retry}")
    private Integer projectMaxRetry;

    public ProjectUpdateService(ProjectServiceFactory projectServiceFactory, UserServiceFactory userServiceFactory,
                                IssueServiceFactory issueServiceFactory) {
        this.projectServiceFactory = projectServiceFactory;
        this.userServiceFactory = userServiceFactory;
        this.issueServiceFactory = issueServiceFactory;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateProject(TrackerParametersBean tracker, boolean forceRetry) throws CriticalDataException {
        logger.info("Update project: {}", tracker);
        ProjectService projectService = projectServiceFactory.getService(tracker.getType());

        try {
            ProjectEntity projectEntity = projectService.initializeProject(tracker, false);

            if (Boolean.TRUE.equals(projectEntity.isActive()) || (forceRetry && projectEntity.getFailureCounter() < projectMaxRetry)) {
                // Update administrator account
                UserService userService = userServiceFactory.getService(tracker.getType());
                userService.update(projectEntity);

                // Update issues
                IssueService issueService = issueServiceFactory.getService(tracker.getType());
                issueService.updateLastModifiedIssues(projectEntity);

                // Delete missing issues
                issueService.deleteMissingIssues(projectEntity);

                // Update last check project
                projectService.updateLastCheckProject(projectEntity);

                // Reactivate project
                if (forceRetry) {
                    projectService.reactivateProject(projectEntity);
                }
            }
        } catch (RuntimeException e) {
            projectService.deactivateProjectOnError(tracker, e);
            logger.error("Error during script execution with this tracker: {}. Please check the errors before reactivating it in the database. Error details => {}", tracker, e.getMessage());
        }
    }
}
