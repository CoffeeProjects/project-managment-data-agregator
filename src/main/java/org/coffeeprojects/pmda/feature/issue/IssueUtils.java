package org.coffeeprojects.pmda.feature.issue;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectUtils;
import org.coffeeprojects.pmda.feature.user.UserEntity;

import java.util.*;

public class IssueUtils {

    private IssueUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    public static void removeDuplicateUsers(List<IssueEntity> issueEntities) {
        Optional.ofNullable(issueEntities)
                .orElse(Collections.emptyList())
                .forEach(i -> {
                    Set<UserEntity> existingUsers = new HashSet<>();
                    existingUsers.add(i.getProject().getAdministrator());

                    i.setAssignee(getNonDuplicateUser(existingUsers, i.getAssignee()));
                    i.setCreator(getNonDuplicateUser(existingUsers, i.getCreator()));
                    i.setReporter(getNonDuplicateUser(existingUsers, i.getReporter()));

                    Optional.ofNullable(i.getChangelog())
                            .orElse(Collections.emptySet())
                            .forEach(c -> c.setAuthor(getNonDuplicateUser(existingUsers, c.getAuthor())));
        });
    }

    private static UserEntity getNonDuplicateUser(Set<UserEntity> existingUsers, UserEntity user) {
        if (user == null) {
            return null;
        }
        for (UserEntity existingUser : existingUsers) {
            if (user.getId().getClientId().equals(existingUser.getId().getClientId())) {
                return existingUser;
            }
        }
        existingUsers.add(user);
        return user;
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
