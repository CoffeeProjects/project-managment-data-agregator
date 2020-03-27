package org.coffeeprojects.pmda.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Issue")
public class IssueEntity implements Serializable {
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public UserEntity getAssignee() {
        return assignee;
    }

    public void setAssignee(UserEntity assignee) {
        this.assignee = assignee;
    }

    public UserEntity getReporter() {
        return reporter;
    }

    public void setReporter(UserEntity reporter) {
        this.reporter = reporter;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public ResolutionEntity getResolution() {
        return resolution;
    }

    public void setResolution(ResolutionEntity resolution) {
        this.resolution = resolution;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public PriorityEntity getPriority() {
        return priority;
    }

    public void setPriority(PriorityEntity priority) {
        this.priority = priority;
    }

    public IssueTypeEntity getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueTypeEntity issueType) {
        this.issueType = issueType;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public Set<VersionEntity> getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(Set<VersionEntity> fixVersions) {
        this.fixVersions = fixVersions;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Set<ComponentEntity> getComponents() {
        return components;
    }

    public void setComponents(Set<ComponentEntity> components) {
        this.components = components;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Set<IssueEntity> getIssueLinks() {
        return issueLinks;
    }

    public void setIssueLinks(Set<IssueEntity> issueLinks) {
        this.issueLinks = issueLinks;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
