package org.coffeeprojects.pmda.tracker;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class TrackerUtils {

    private static final Logger log = LoggerFactory.getLogger(TrackerUtils.class);

    public static void fillIdsFromIssueEntities(ProjectEntity projectEntity, List<IssueEntity> issueEntities) {
        issueEntities.forEach(p -> {
            fillIdsFromIssueEntity(projectEntity, p);
        });
    }

    public static void fillIdsFromIssueEntity(ProjectEntity projectEntity, IssueEntity issueEntity) {
        fillIds(projectEntity, issueEntity);
        if (issueEntity != null) {
            if (issueEntity.getAssignee() != null) {
                fillIds(projectEntity, issueEntity.getAssignee());
            } if (issueEntity.getCreator() != null) {
                fillIds(projectEntity, issueEntity.getCreator());
            } if (issueEntity.getReporter() != null) {
                fillIds(projectEntity, issueEntity.getReporter());
            } if (issueEntity.getStatus() != null) {
                fillIds(projectEntity, issueEntity.getStatus());
            } if (issueEntity.getResolution() != null) {
                fillIds(projectEntity, issueEntity.getResolution());
            } if (issueEntity.getPriority() != null) {
                fillIds(projectEntity, issueEntity.getPriority());
            } if (issueEntity.getIssueType() != null) {
                fillIds(projectEntity, issueEntity.getIssueType());
            } if (issueEntity.getProject() != null) {
                fillIds(projectEntity, issueEntity.getProject());
            } if (issueEntity.getFixVersions() != null) {
                fillIds(projectEntity, issueEntity.getFixVersions());
            } if (issueEntity.getComponents() != null) {
                fillIds(projectEntity, issueEntity.getComponents());
            } if (issueEntity.getSprints() != null) {
                fillIds(projectEntity, issueEntity.getSprints());
            }
        }
    }

    private static <T extends BaseEntity> void fillIds(ProjectEntity projectEntity, Set<T> baseEntities) {
        baseEntities.forEach(p -> {
            fillIds(projectEntity, p);
        });
    }

    private static void fillIds(ProjectEntity projectEntity, BaseEntity baseEntity) {
        if (baseEntity != null && baseEntity.getId() != null && projectEntity != null && projectEntity.getId() != null) {
            if (projectEntity.getId().getTrackerLocalId() != null && projectEntity.getId().getTrackerType() != null) {
                baseEntity.getId().setTrackerType(projectEntity.getId().getTrackerType());
                baseEntity.getId().setTrackerLocalId(projectEntity.getId().getTrackerLocalId());
            } else
                log.error("trackerId and / or trackerType not entered for the project ID " + projectEntity.getId().getClientId());
        } else {
            log.error("baseEntity or projectEntity could not be null");
        }
    }

    public static Instant getInstantFromTimezone(String timezone) {
        if (timezone != null && !timezone.isBlank() && !"<null>".equals(timezone)) {
            return LocalDateTime.parse(timezone, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
                    .atZone(ZoneId.systemDefault()).toInstant();
        }
        return null;
    }
}
