package org.coffeeprojects.pmda.issue.jirabean;

import java.util.Objects;

public class IssueJiraBean {

    private String id;

    private String key;

    private String expand;

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

    public String getExpand() {
        return expand;
    }

    public IssueJiraBean setExpand(String expand) {
        this.expand = expand;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueJiraBean that = (IssueJiraBean) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(key, that.key) &&
                Objects.equals(expand, that.expand) &&
                Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, expand, fields);
    }

    @Override
    public String toString() {
        return "IssueJiraBean{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", expand='" + expand + '\'' +
                ", fields=" + fields +
                '}';
    }
}
