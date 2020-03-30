package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.component.ComponentEntity;
import org.coffeeprojects.pmda.entity.AuditableEntity;
import org.coffeeprojects.pmda.issueType.IssueTypeEntity;
import org.coffeeprojects.pmda.priority.PriorityEntity;
import org.coffeeprojects.pmda.project.ProjectEntity;
import org.coffeeprojects.pmda.resolution.ResolutionEntity;
import org.coffeeprojects.pmda.sprint.SprintEntity;
import org.coffeeprojects.pmda.status.StatusEntity;
import org.coffeeprojects.pmda.user.UserEntity;
import org.coffeeprojects.pmda.version.VersionEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Issue")
public class IssueEntity extends AuditableEntity implements Serializable {
    @Id
    private String id;

    private String key;

    private UserEntity assignee;

    private UserEntity reporter;

    private UserEntity creator;

    private String summary;

    private StatusEntity status;

    private ResolutionEntity resolution;

    @Temporal(TemporalType.TIMESTAMP)
    private Date resolutionDate;

    private PriorityEntity priority;

    private IssueTypeEntity issueType;

    private ProjectEntity project;

    @OneToMany
    private Set<VersionEntity> fixVersions;

    @ElementCollection
    private List<String> labels;

    @OneToMany
    private Set<ComponentEntity> components;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @OneToMany
    private Set<IssueEntity> issueLinks;

    @OneToMany
    private Set<SprintEntity> sprints;

    public String getId() {
        return id;
    }

    public IssueEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public IssueEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public UserEntity getAssignee() {
        return assignee;
    }

    public IssueEntity setAssignee(UserEntity assignee) {
        this.assignee = assignee;
        return this;
    }

    public UserEntity getReporter() {
        return reporter;
    }

    public IssueEntity setReporter(UserEntity reporter) {
        this.reporter = reporter;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public IssueEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public IssueEntity setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public IssueEntity setStatus(StatusEntity status) {
        this.status = status;
        return this;
    }

    public ResolutionEntity getResolution() {
        return resolution;
    }

    public IssueEntity setResolution(ResolutionEntity resolution) {
        this.resolution = resolution;
        return this;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public IssueEntity setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
        return this;
    }

    public PriorityEntity getPriority() {
        return priority;
    }

    public IssueEntity setPriority(PriorityEntity priority) {
        this.priority = priority;
        return this;
    }

    public IssueTypeEntity getIssueType() {
        return issueType;
    }

    public IssueEntity setIssueType(IssueTypeEntity issueType) {
        this.issueType = issueType;
        return this;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public IssueEntity setProject(ProjectEntity project) {
        this.project = project;
        return this;
    }

    public Set<VersionEntity> getFixVersions() {
        return fixVersions;
    }

    public IssueEntity setFixVersions(Set<VersionEntity> fixVersions) {
        this.fixVersions = fixVersions;
        return this;
    }

    public List<String> getLabels() {
        return labels;
    }

    public IssueEntity setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    public Set<ComponentEntity> getComponents() {
        return components;
    }

    public IssueEntity setComponents(Set<ComponentEntity> components) {
        this.components = components;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public IssueEntity setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public IssueEntity setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public Set<IssueEntity> getIssueLinks() {
        return issueLinks;
    }

    public IssueEntity setIssueLinks(Set<IssueEntity> issueLinks) {
        this.issueLinks = issueLinks;
        return this;
    }

    public Set<SprintEntity> getSprints() {
        return sprints;
    }

    public void setSprints(Set<SprintEntity> sprints) {
        this.sprints = sprints;
    }
}
