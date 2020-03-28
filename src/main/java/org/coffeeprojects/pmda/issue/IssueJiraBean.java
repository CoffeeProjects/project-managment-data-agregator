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

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public UserJiraBean getAssignee() {
        return assignee;
    }

    public void setAssignee(UserJiraBean assignee) {
        this.assignee = assignee;
    }

    public UserJiraBean getReporter() {
        return reporter;
    }

    public void setReporter(UserJiraBean reporter) {
        this.reporter = reporter;
    }

    public UserJiraBean getCreator() {
        return creator;
    }

    public void setCreator(UserJiraBean creator) {
        this.creator = creator;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public StatusJiraBean getStatus() {
        return status;
    }

    public void setStatus(StatusJiraBean status) {
        this.status = status;
    }

    public ResolutionJiraBean getResolution() {
        return resolution;
    }

    public void setResolution(ResolutionJiraBean resolution) {
        this.resolution = resolution;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public PriorityJiraBean getPriority() {
        return priority;
    }

    public void setPriority(PriorityJiraBean priority) {
        this.priority = priority;
    }

    public IssueTypeJiraBean getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueTypeJiraBean issueType) {
        this.issueType = issueType;
    }

    public ProjectJiraBean getProject() {
        return project;
    }

    public void setProject(ProjectJiraBean project) {
        this.project = project;
    }

    public Set<VersionJiraBean> getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(Set<VersionJiraBean> fixVersions) {
        this.fixVersions = fixVersions;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Set<ComponentJiraBean> getComponents() {
        return components;
    }

    public void setComponents(Set<ComponentJiraBean> components) {
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

    public Set<IssueJiraBean> getIssueLinks() {
        return issueLinks;
    }

    public void setIssueLinks(Set<IssueJiraBean> issueLinks) {
        this.issueLinks = issueLinks;
    }

}
