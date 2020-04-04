package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.feature.component.ComponentEntity;
import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issueType.IssueTypeEntity;
import org.coffeeprojects.pmda.feature.priority.PriorityEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.resolution.ResolutionEntity;
import org.coffeeprojects.pmda.feature.sprint.SprintEntity;
import org.coffeeprojects.pmda.feature.status.StatusEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.version.VersionEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "issue")
public class IssueEntity extends BaseEntity implements Serializable {

    private String key;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity assignee;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity reporter;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity creator;

    private String summary;

    @OneToOne(cascade = CascadeType.ALL)
    private StatusEntity status;

    @OneToOne(cascade = CascadeType.ALL)
    private ResolutionEntity resolution;

    @Temporal(TemporalType.TIMESTAMP)
    private Date resolutionDate;

    @OneToOne(cascade = CascadeType.ALL)
    private PriorityEntity priority;

    @OneToOne(cascade = CascadeType.ALL)
    private IssueTypeEntity issueType;

    @OneToOne(cascade = CascadeType.ALL)
    private ProjectEntity project;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "issue_version",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "version_id"))
    private Set<VersionEntity> fixVersions;

    @ElementCollection
    private List<String> labels;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "issue_component",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id"))
    private Set<ComponentEntity> components;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "issue_sprint",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "sprint_id"))
    private Set<SprintEntity> sprints;

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

    public Set<SprintEntity> getSprints() {
        return sprints;
    }

    public IssueEntity setSprints(Set<SprintEntity> sprints) {
        this.sprints = sprints;
        return this;
    }
}
