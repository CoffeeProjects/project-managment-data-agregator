package org.coffeeprojects.pmda.issue.jirabean;

import java.util.Objects;

public class IssueLinkJiraTypeBean {

    private String id;
    private String name;
    private String inward;
    private String outward;

    public String getId() {
        return id;
    }

    public IssueLinkJiraTypeBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInward() {
        return inward;
    }

    public void setInward(String inward) {
        this.inward = inward;
    }

    public String getOutward() {
        return outward;
    }

    public void setOutward(String outward) {
        this.outward = outward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueLinkJiraTypeBean that = (IssueLinkJiraTypeBean) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(inward, that.inward) &&
                Objects.equals(outward, that.outward);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inward, outward);
    }

    @Override
    public String toString() {
        return "IssueLinkJiraTypeBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", inward='" + inward + '\'' +
                ", outward='" + outward + '\'' +
                '}';
    }
}
