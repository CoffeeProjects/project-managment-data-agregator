package org.coffeeprojects.pmda.tracker;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TrackerUtils {

    private static final Logger log = LoggerFactory.getLogger(TrackerUtils.class);
    public static final String NULL = "<null>";
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private TrackerUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void fillIdsFromUserEntity(ProjectEntity projectEntity, UserEntity userEntity) {
        fillIds(projectEntity, userEntity);
    }

    public static void fillIdsFromIssueEntities(ProjectEntity projectEntity, List<IssueEntity> issueEntities) {
        issueEntities.forEach(p -> fillIdsFromIssueEntity(projectEntity, p));
    }

    public static void fillIdsFromIssueEntity(ProjectEntity projectEntity, IssueEntity issueEntity) {
        fillIds(projectEntity, issueEntity);
        Optional.ofNullable(issueEntity).ifPresent(i -> {
            Optional.ofNullable(i.getAssignee()).ifPresent(assignee -> fillIds(projectEntity, assignee));
            Optional.ofNullable(i.getCreator()).ifPresent(creator -> fillIds(projectEntity, creator));
            Optional.ofNullable(i.getReporter()).ifPresent(reporter -> fillIds(projectEntity, reporter));
            Optional.ofNullable(i.getStatus()).ifPresent(status -> fillIds(projectEntity, status));
            Optional.ofNullable(i.getResolution()).ifPresent(resolution -> fillIds(projectEntity, resolution));
            Optional.ofNullable(i.getPriority()).ifPresent(priority -> fillIds(projectEntity, priority));
            Optional.ofNullable(i.getIssueType()).ifPresent(issueType -> fillIds(projectEntity, issueType));
            Optional.ofNullable(i.getProject()).ifPresent(project -> fillIds(projectEntity, project));
            Optional.ofNullable(i.getFixVersions()).ifPresent(fixVersions -> fillIds(projectEntity, fixVersions));
            Optional.ofNullable(i.getComponents()).ifPresent(components -> fillIds(projectEntity, components));
            Optional.ofNullable(i.getSprints()).ifPresent(sprints -> fillIds(projectEntity, sprints));
            Optional.ofNullable(i.getChangelog()).ifPresent(changelog -> {
                fillIds(projectEntity, changelog);
                changelog.forEach(c -> fillIds(projectEntity, c.getAuthor()));
            });
        });
    }

    private static <T extends BaseEntity> void fillIds(ProjectEntity projectEntity, Set<T> baseEntities) {
        baseEntities.forEach(p -> fillIds(projectEntity, p));
    }

    private static void fillIds(ProjectEntity projectEntity, BaseEntity baseEntity) {
        Optional.ofNullable(baseEntity)
                .map(BaseEntity::getId)
                .ifPresentOrElse(bId -> Optional.ofNullable(projectEntity)
                        .map(ProjectEntity::getId)
                        .filter(p -> p.getTrackerLocalId() != null && p.getTrackerType() != null)
                        .ifPresentOrElse(pId -> {
                            bId.setTrackerType(pId.getTrackerType());
                            bId.setTrackerLocalId(pId.getTrackerLocalId());
                        }, () -> log.error("trackerId and / or trackerType not entered for this project : {}", projectEntity)),
                        () -> log.error("BaseEntity or ProjectEntity could not be null"));
    }

    public static Instant getInstantFromTimezone(String timezone) {
        try {
            return Optional.ofNullable(timezone)
                    .filter(s -> !s.isBlank())
                    .filter(s -> !NULL.equals(s))
                    .map(s -> LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DATETIME_FORMATTER)).atZone(ZoneId.systemDefault()).toInstant())
                    .orElse(null);
        } catch (DateTimeParseException e) {
            log.error("Unable to parse in Instant with timezone : {}", timezone);
            return null;
        }
    }
}
