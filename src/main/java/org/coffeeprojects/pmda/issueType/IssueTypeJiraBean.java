package org.coffeeprojects.pmda.issueType;

public class IssueTypeJiraBean {

    private String id;

    private String name;

    private String description;

    public String getId() {
        return id;
    }

    public IssueTypeJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IssueTypeJiraBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueTypeJiraBean setDescription(String description) {
        this.description = description;
        return this;
    }
}
