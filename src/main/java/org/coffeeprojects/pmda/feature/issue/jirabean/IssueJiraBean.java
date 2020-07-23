package org.coffeeprojects.pmda.feature.issue.jirabean;

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
    public String toString() {
        return "IssueJiraBean{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", expand='" + expand + '\'' +
                ", fields=" + fields +
                '}';
    }
}
