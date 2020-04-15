package org.coffeeprojects.pmda.feature.issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IssueUtils {

    private IssueUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> getClientIdFromIssueEntities(List<IssueEntity> issueEntities) {
        List<String> issueEntitiesId = new ArrayList();
        issueEntities.forEach(i -> issueEntitiesId.add(i.getId().getClientId()));
        return issueEntitiesId;
    }

    // TODO : Réfléchir à une solution plus simple
    public static List<IssueEntity> getIssueEntitiesDelta(List<IssueEntity> localIssueEntities, List<IssueEntity> clientIssueEntities) {
        List<IssueEntity> notFoundIssues = new ArrayList();

        localIssueEntities.forEach(localIssue -> {
            clientIssueEntities.stream()
                    .filter(clientIssue -> clientIssue.getId().getClientId().equals(localIssue.getId().getClientId()))
                    .findFirst()
                    .ifPresent(clientIssue -> notFoundIssues.add(localIssue));
        });

        return notFoundIssues;
    }

    public static List<IssueEntity> getUnresolvedIssueEntities(List<IssueEntity> issueEntities) {
        return issueEntities.stream()
                .filter(i -> i.getResolutionDate() != null)
                .collect(Collectors.toList());
    }
}
