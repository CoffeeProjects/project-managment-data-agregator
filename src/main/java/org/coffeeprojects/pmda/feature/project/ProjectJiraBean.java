package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.feature.user.UserJiraBean;

import java.util.Objects;

public class ProjectJiraBean {

    private String id;

    private String key;

    private String name;

    private UserJiraBean lead;

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

    public UserJiraBean getLead() {
        return lead;
    }

    public void setLead(UserJiraBean lead) {
        this.lead = lead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectJiraBean that = (ProjectJiraBean) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(key, that.key) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lead, that.lead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key, name, lead);
    }

    @Override
    public String toString() {
        return "ProjectJiraBean{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", lead=" + lead +
                '}';
    }
}
