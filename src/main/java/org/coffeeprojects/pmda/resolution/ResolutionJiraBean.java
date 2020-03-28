package org.coffeeprojects.pmda.resolution;

public class ResolutionJiraBean {

    private String id;

    private String name;

    private String description;

    public String getId() {
        return id;
    }

    public ResolutionJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResolutionJiraBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ResolutionJiraBean setDescription(String description) {
        this.description = description;
        return this;
    }
}
