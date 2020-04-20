package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.component.ComponentEntity;
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
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "issue")
public class IssueEntity extends BaseEntity implements Serializable {

    private String key;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private UserEntity assignee;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private UserEntity reporter;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private UserEntity creator;

    private String summary;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private StatusEntity status;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private ResolutionEntity resolution;

    private Instant resolutionDate;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private PriorityEntity priority;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private IssueTypeEntity issueType;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private ProjectEntity project;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "issue_version",
            joinColumns = {@JoinColumn(name = "issue_client_id", referencedColumnName="clientId"),
                    @JoinColumn(name = "issue_tracker_local_id", referencedColumnName="trackerLocalId"),
                    @JoinColumn(name = "issue_tracker_type", referencedColumnName="trackerType")},
            inverseJoinColumns = {@JoinColumn(name = "version_client_id", referencedColumnName="clientId"),
                    @JoinColumn(name = "version_tracker_local_id", referencedColumnName="trackerLocalId"),
                    @JoinColumn(name = "version_tracker_type", referencedColumnName="trackerType")})
    private Set<VersionEntity> fixVersions;

    @ElementCollection
    private List<String> labels;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "issue_component",
            joinColumns = {@JoinColumn(name = "issue_client_id", referencedColumnName="clientId"),
                    @JoinColumn(name = "issue_tracker_local_id", referencedColumnName="trackerLocalId"),
                    @JoinColumn(name = "issue_tracker_type", referencedColumnName="trackerType")},
            inverseJoinColumns = {@JoinColumn(name = "component_client_id", referencedColumnName="clientId"),
                    @JoinColumn(name = "component_tracker_local_id", referencedColumnName="trackerLocalId"),
                    @JoinColumn(name = "component_tracker_type", referencedColumnName="trackerType")})
    private Set<ComponentEntity> components;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "issue_sprint",
            joinColumns = {@JoinColumn(name = "issue_client_id", referencedColumnName="clientId"),
                    @JoinColumn(name = "issue_tracker_local_id", referencedColumnName="trackerLocalId"),
                    @JoinColumn(name = "issue_tracker_type", referencedColumnName="trackerType")},
            inverseJoinColumns = {@JoinColumn(name = "sprint_client_id", referencedColumnName="clientId"),
                    @JoinColumn(name = "sprint_tracker_local_id", referencedColumnName="trackerLocalId"),
                    @JoinColumn(name = "sprint_tracker_type", referencedColumnName="trackerType")})
    private Set<SprintEntity> sprints;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "issue_client_id", referencedColumnName="clientId")
    @JoinColumn(name = "issue_tracker_local_id", referencedColumnName="trackerLocalId")
    @JoinColumn(name = "issue_tracker_type", referencedColumnName="trackerType")
    private Set<IssueCustomField> issueCustomFields;

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

    public Instant getResolutionDate() {
        return resolutionDate;
    }

    public IssueEntity setResolutionDate(Instant resolutionDate) {
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

    public Set<IssueCustomField> getIssueCustomFields() {
        return issueCustomFields;
    }

    public IssueEntity setIssueCustomFields(Set<IssueCustomField> issueCustomFields) {
        this.issueCustomFields = issueCustomFields;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueEntity that = (IssueEntity) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(assignee, that.assignee) &&
                Objects.equals(reporter, that.reporter) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(status, that.status) &&
                Objects.equals(resolution, that.resolution) &&
                Objects.equals(resolutionDate, that.resolutionDate) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(issueType, that.issueType) &&
                Objects.equals(project, that.project) &&
                Objects.equals(fixVersions, that.fixVersions) &&
                Objects.equals(labels, that.labels) &&
                Objects.equals(components, that.components) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated) &&
                Objects.equals(sprints, that.sprints) &&
                Objects.equals(issueCustomFields, that.issueCustomFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, assignee, reporter, creator, summary, status, resolution, resolutionDate, priority, issueType, project, fixVersions, labels, components, created, updated, sprints, issueCustomFields);
    }

    @Override
    public String toString() {
        return "IssueEntity{" +
                "key='" + key + '\'' +
                ", assignee=" + assignee +
                ", reporter=" + reporter +
                ", creator=" + creator +
                ", summary='" + summary + '\'' +
                ", status=" + status +
                ", resolution=" + resolution +
                ", resolutionDate=" + resolutionDate +
                ", priority=" + priority +
                ", issueType=" + issueType +
                ", project=" + project +
                ", fixVersions=" + fixVersions +
                ", labels=" + labels +
                ", components=" + components +
                ", created=" + created +
                ", updated=" + updated +
                ", sprints=" + sprints +
                ", issueCustomFields=" + issueCustomFields +
                '}';
    }
}
