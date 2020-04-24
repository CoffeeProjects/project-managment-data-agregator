package org.coffeeprojects.pmda.feature.issue;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectUtils;
import org.coffeeprojects.pmda.feature.user.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class IssueUtils {

    private IssueUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    public static void removeDuplicateUsers(List<IssueEntity> issueEntities) {
        issueEntities.forEach(i -> {
            UserEntity administrator = i.getProject().getAdministrator();
            UserEntity creator = i.getCreator();
            UserEntity assignee = i.getAssignee();
            UserEntity reporter = i.getReporter();

            assignee = assignee != null && creator != null && assignee.getId().getClientId().equals(creator.getId().getClientId()) ? creator : assignee;
            assignee = assignee != null && reporter != null && assignee.getId().getClientId().equals(reporter.getId().getClientId()) ? reporter : assignee;
            assignee = assignee != null && administrator != null && assignee.getId().getClientId().equals(administrator.getId().getClientId()) ? administrator : assignee;

            creator = creator != null && reporter != null && creator.getId().getClientId().equals(reporter.getId().getClientId()) ? reporter : creator;
            creator = creator != null && administrator != null && creator.getId().getClientId().equals(administrator.getId().getClientId()) ? administrator : creator;

            reporter = reporter != null && administrator != null && reporter.getId().getClientId().equals(administrator.getId().getClientId()) ? administrator : reporter;

            i.setCreator(creator);
            i.setAssignee(assignee);
            i.setReporter(reporter);
        });
    }

    public static List<String> getKeysFromIssueEntities(List<IssueEntity> issueEntities) {
        List<String> issueEntitiesId = new ArrayList<>();

            Optional.ofNullable(issueEntities)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(i -> i.getId() != null)
                    .filter(i -> i.getId().getClientId() != null)
                    .filter(i -> i.getKey() != null)
                    .forEach(i -> issueEntitiesId.add(i.getKey()));

        return issueEntitiesId;
    }

    // TODO : Réfléchir à une solution plus simple
    public static List<IssueEntity> getIssueEntitiesDelta(List<IssueEntity> localIssueEntities, List<IssueEntity> clientIssueEntities) {
        List<IssueEntity> notFoundIssues = new ArrayList<>();

        Optional.ofNullable(localIssueEntities)
                .orElse(Collections.emptyList())
                .stream()
                .filter(localIssue -> localIssue.getKey() != null)
                .forEach(localIssue -> {

                    IssueEntity matchIssueEntity = Optional.ofNullable(clientIssueEntities)
                            .orElse(Collections.emptyList())
                            .stream()
                            .filter(clientIssue -> clientIssue.getKey() != null)
                            .filter(clientIssue -> clientIssue.getKey().equals(localIssue.getKey()))
                            .findFirst()
                            .orElse(null);

                            if (matchIssueEntity == null) {
                                notFoundIssues.add(localIssue);
                            }
                        }
                );

        return notFoundIssues;
    }

    public static String getFields(ProjectEntity projectEntity, String defaultFields) {
        String projectFields = StringUtils.join(ProjectUtils.getClientNameCustomFields(projectEntity), ",");
        projectFields = StringUtils.isNotEmpty(projectFields) ? defaultFields + "," + StringUtils.join(ProjectUtils.getClientNameCustomFields(projectEntity), ",") : defaultFields;
        return projectFields;
    }
}