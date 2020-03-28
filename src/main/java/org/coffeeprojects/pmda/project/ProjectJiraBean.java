package org.coffeeprojects.pmda.project;

public class ProjectJiraBean {

    private String id;

    private String key;

    private String name;

    public String getId() {
        return id;
    }

    public ProjectJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ProjectJiraBean setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectJiraBean setName(String name) {
        this.name = name;
        return this;
    }
}
