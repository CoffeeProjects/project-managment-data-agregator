package org.coffeeprojects.pmda.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.coffeeprojects.pmda.component.ComponentJiraBean;
import org.coffeeprojects.pmda.issueType.IssueTypeJiraBean;
import org.coffeeprojects.pmda.priority.PriorityJiraBean;
import org.coffeeprojects.pmda.project.ProjectJiraBean;
import org.coffeeprojects.pmda.resolution.ResolutionJiraBean;
import org.coffeeprojects.pmda.status.StatusJiraBean;
import org.coffeeprojects.pmda.user.UserJiraBean;
import org.coffeeprojects.pmda.version.VersionJiraBean;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FieldsJiraBean {
    private UserJiraBean assignee;

    private UserJiraBean reporter;

    private UserJiraBean creator;

    private String summary;

    private StatusJiraBean status;

    private ResolutionJiraBean resolution;

    private Date resolutionDate;

    private PriorityJiraBean priority;

    @JsonProperty("issuetype")
    private IssueTypeJiraBean issueType;

    private ProjectJiraBean project;

    private Set<VersionJiraBean> fixVersions;

    private List<String> labels;

    private Set<ComponentJiraBean> components;

    private Date created;

    private Date updated;

    private Set<IssueJiraBean> issueLinks;

    public UserJiraBean getAssignee() {
        return assignee;
    }

    public FieldsJiraBean setAssignee(UserJiraBean assignee) {
        this.assignee = assignee;
        return this;
    }

    public UserJiraBean getReporter() {
        return reporter;
    }

    public FieldsJiraBean setReporter(UserJiraBean reporter) {
        this.reporter = reporter;
        return this;
    }

    public UserJiraBean getCreator() {
        return creator;
    }

    public FieldsJiraBean setCreator(UserJiraBean creator) {
        this.creator = creator;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public FieldsJiraBean setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public StatusJiraBean getStatus() {
        return status;
    }

    public FieldsJiraBean setStatus(StatusJiraBean status) {
        this.status = status;
        return this;
    }

    public ResolutionJiraBean getResolution() {
        return resolution;
    }

    public FieldsJiraBean setResolution(ResolutionJiraBean resolution) {
        this.resolution = resolution;
        return this;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public FieldsJiraBean setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
        return this;
    }

    public PriorityJiraBean getPriority() {
        return priority;
    }

    public FieldsJiraBean setPriority(PriorityJiraBean priority) {
        this.priority = priority;
        return this;
    }

    public IssueTypeJiraBean getIssueType() {
        return issueType;
    }

    public FieldsJiraBean setIssueType(IssueTypeJiraBean issueType) {
        this.issueType = issueType;
        return this;
    }

    public ProjectJiraBean getProject() {
        return project;
    }

    public FieldsJiraBean setProject(ProjectJiraBean project) {
        this.project = project;
        return this;
    }

    public Set<VersionJiraBean> getFixVersions() {
        return fixVersions;
    }

    public FieldsJiraBean setFixVersions(Set<VersionJiraBean> fixVersions) {
        this.fixVersions = fixVersions;
        return this;
    }

    public List<String> getLabels() {
        return labels;
    }

    public FieldsJiraBean setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    public Set<ComponentJiraBean> getComponents() {
        return components;
    }

    public FieldsJiraBean setComponents(Set<ComponentJiraBean> components) {
        this.components = components;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public FieldsJiraBean setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public FieldsJiraBean setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public Set<IssueJiraBean> getIssueLinks() {
        return issueLinks;
    }

    public FieldsJiraBean setIssueLinks(Set<IssueJiraBean> issueLinks) {
        this.issueLinks = issueLinks;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldsJiraBean that = (FieldsJiraBean) o;
        return Objects.equals(assignee, that.assignee) &&
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
                Objects.equals(issueLinks, that.issueLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignee, reporter, creator, summary, status, resolution, resolutionDate, priority, issueType, project, fixVersions, labels, components, created, updated, issueLinks);
    }

    @Override
    public String toString() {
        return "FieldsJiraBean{" +
                "assignee=" + assignee +
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
                ", issueLinks=" + issueLinks +
                '}';
    }
}
