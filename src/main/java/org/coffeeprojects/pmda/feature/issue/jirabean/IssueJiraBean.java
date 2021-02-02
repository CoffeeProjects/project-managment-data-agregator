package org.coffeeprojects.pmda.feature.issue.jirabean;

import org.coffeeprojects.pmda.feature.changelog.jirabean.ChangelogJiraBean;

public class IssueJiraBean {

    private String id;

    private String key;

    private ChangelogJiraBean changelog;

    private FieldsJiraBean fields;

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

    public ChangelogJiraBean getChangelog() {
        return changelog;
    }

    public IssueJiraBean setChangelog(ChangelogJiraBean changelog) {
        this.changelog = changelog;
        return this;
    }

    public FieldsJiraBean getFields() {
        return fields;
    }

    public IssueJiraBean setFields(FieldsJiraBean fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public String toString() {
        return "IssueJiraBean{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", changelog='" + changelog + '\'' +
                ", fields=" + fields +
                '}';
    }
}