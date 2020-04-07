package org.coffeeprojects.pmda.feature.version;

public class VersionJiraBean {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public VersionJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VersionJiraBean setName(String name) {
        this.name = name;
        return this;
    }
}
