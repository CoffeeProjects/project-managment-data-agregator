package org.coffeeprojects.pmda.feature.issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IssueUtils {

    private IssueUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> getKeysFromIssueEntities(List<IssueEntity> issueEntities) {
        List<String> issueEntitiesId = new ArrayList();

        if (issueEntities != null) {
            issueEntities
                    .stream()
                    .filter(i -> i.getId() != null)
                    .filter(i -> i.getId().getClientId() != null)
                    .filter(i -> i.getKey() != null)
                    .forEach(i -> issueEntitiesId.add(i.getKey()));
        }

        return issueEntitiesId;
    }

    // TODO : Réfléchir à une solution plus simple
    public static List<IssueEntity> getIssueEntitiesDelta(List<IssueEntity> localIssueEntities, List<IssueEntity> clientIssueEntities) {
        List<IssueEntity> notFoundIssues = new ArrayList();

        if (localIssueEntities != null && clientIssueEntities != null) {
            localIssueEntities.stream()
                    .filter(localIssue -> localIssue.getKey() != null)
                    .forEach(localIssue -> {

                        IssueEntity matchIssueEntity = clientIssueEntities.stream()
                                .filter(clientIssue -> clientIssue.getKey() != null)
                                .filter(clientIssue -> clientIssue.getKey().equals(localIssue.getKey()))
                                .findFirst()
                                .orElse(null);

                        if (matchIssueEntity == null) {
                            notFoundIssues.add(localIssue);
                        }
                            }
                    );
        }

        return notFoundIssues;
    }

    public static List<IssueEntity> getUnresolvedIssueEntities(List<IssueEntity> issueEntities) {
        List<IssueEntity> resolvedIssueEntities = new ArrayList();

        if (issueEntities != null) {
            resolvedIssueEntities = issueEntities.stream()
                    .filter(i -> i.getResolutionDate() == null)
                    .collect(Collectors.toList());
        }

        return resolvedIssueEntities;
    }
}
