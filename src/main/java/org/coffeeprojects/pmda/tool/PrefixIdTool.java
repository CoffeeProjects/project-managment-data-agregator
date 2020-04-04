package org.coffeeprojects.pmda.tool;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;

import java.util.List;
import java.util.Set;

public class PrefixIdTool {

    public static void fillPrefixIdFromissueEntities(ProjectEntity projectEntity, List<IssueEntity> issueEntiies) {
        for (IssueEntity issueEntity : issueEntiies) {
            fillPrefixIdFromissueEntity(projectEntity, issueEntity);
        }
    }

    public static void fillPrefixIdFromissueEntity(ProjectEntity projectEntity, IssueEntity issueEntity) {
        fillPrefixId(projectEntity, issueEntity);
        if (issueEntity != null) {
            if (issueEntity.getAssignee() != null) {
                fillPrefixId(projectEntity, issueEntity.getAssignee());
            } if (issueEntity.getCreator() != null) {
                fillPrefixId(projectEntity, issueEntity.getCreator());
            } if (issueEntity.getReporter() != null) {
                fillPrefixId(projectEntity, issueEntity.getReporter());
            } if (issueEntity.getStatus() != null) {
                fillPrefixId(projectEntity, issueEntity.getStatus());
            } if (issueEntity.getResolution() != null) {
                fillPrefixId(projectEntity, issueEntity.getResolution());
            } if (issueEntity.getPriority() != null) {
                fillPrefixId(projectEntity, issueEntity.getPriority());
            } if (issueEntity.getIssueType() != null) {
                fillPrefixId(projectEntity, issueEntity.getIssueType());
            } if (issueEntity.getFixVersions() != null) {
                fillPrefixId(projectEntity, issueEntity.getFixVersions());
            } if (issueEntity.getComponents() != null) {
                fillPrefixId(projectEntity, issueEntity.getComponents());
            } if (issueEntity.getSprints() != null) {
                fillPrefixId(projectEntity, issueEntity.getSprints());
            }
        }
    }

    public static <T extends BaseEntity> void fillPrefixId(ProjectEntity projectEntity, Set<T> baseEntities) {
        for (BaseEntity baseEntity : baseEntities) {
            fillPrefixId(projectEntity, baseEntity);
        }
    }

    public static void fillPrefixId(ProjectEntity projectEntity, BaseEntity baseEntity) {
        if (baseEntity != null) {
            baseEntity.setId(projectEntity.getTrackerType() + "-" + projectEntity.getTrackerNumber() + "-" + baseEntity.getId());
        }
    }
}
