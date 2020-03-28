package org.coffeeprojects.pmda.issue;

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
import java.util.Set;

public class IssueJiraBean {

    private String id;

    private String key;

    private UserJiraBean assignee;

    private UserJiraBean reporter;

    private UserJiraBean creator;

    private String summary;

    private StatusJiraBean status;

    private ResolutionJiraBean resolution;

    private Date resolutionDate;

    private PriorityJiraBean priority;

    private IssueTypeJiraBean issueType;

    private ProjectJiraBean project;

    private Set<VersionJiraBean> fixVersions;

    private List<String> labels;

    private Set<ComponentJiraBean> components;

    private Date created;

    private Date updated;

    private Set<IssueJiraBean> issueLinks;

    public String getId() {
        return id;
    }

    public IssueJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public IssueJiraBean setKey(String key) {
        this.key = key;
        return this;
    }

    public UserJiraBean getAssignee() {
        return assignee;
    }

    public IssueJiraBean setAssignee(UserJiraBean assignee) {
        this.assignee = assignee;
        return this;
    }

    public UserJiraBean getReporter() {
        return reporter;
    }

    public IssueJiraBean setReporter(UserJiraBean reporter) {
        this.reporter = reporter;
        return this;
    }

    public UserJiraBean getCreator() {
        return creator;
    }

    public IssueJiraBean setCreator(UserJiraBean creator) {
        this.creator = creator;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public IssueJiraBean setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public StatusJiraBean getStatus() {
        return status;
    }

    public IssueJiraBean setStatus(StatusJiraBean status) {
        this.status = status;
        return this;
    }

    public ResolutionJiraBean getResolution() {
        return resolution;
    }

    public IssueJiraBean setResolution(ResolutionJiraBean resolution) {
        this.resolution = resolution;
        return this;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public IssueJiraBean setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
        return this;
    }

    public PriorityJiraBean getPriority() {
        return priority;
    }

    public IssueJiraBean setPriority(PriorityJiraBean priority) {
        this.priority = priority;
        return this;
    }

    public IssueTypeJiraBean getIssueType() {
        return issueType;
    }

    public IssueJiraBean setIssueType(IssueTypeJiraBean issueType) {
        this.issueType = issueType;
        return this;
    }

    public ProjectJiraBean getProject() {
        return project;
    }

    public IssueJiraBean setProject(ProjectJiraBean project) {
        this.project = project;
        return this;
    }

    public Set<VersionJiraBean> getFixVersions() {
        return fixVersions;
    }

    public IssueJiraBean setFixVersions(Set<VersionJiraBean> fixVersions) {
        this.fixVersions = fixVersions;
        return this;
    }

    public List<String> getLabels() {
        return labels;
    }

    public IssueJiraBean setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    public Set<ComponentJiraBean> getComponents() {
        return components;
    }

    public IssueJiraBean setComponents(Set<ComponentJiraBean> components) {
        this.components = components;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public IssueJiraBean setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public IssueJiraBean setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    public Set<IssueJiraBean> getIssueLinks() {
        return issueLinks;
    }

    public IssueJiraBean setIssueLinks(Set<IssueJiraBean> issueLinks) {
        this.issueLinks = issueLinks;
        return this;
    }
}
