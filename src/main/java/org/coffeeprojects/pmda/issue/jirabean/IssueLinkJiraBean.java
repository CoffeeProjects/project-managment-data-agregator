package org.coffeeprojects.pmda.issue.jirabean;

import java.util.Objects;

public class IssueLinkJiraBean {

    private IssueLinkJiraTypeBean type;
    private IssueJiraBean inwardIssue;

    public IssueLinkJiraTypeBean getType() {
        return type;
    }

    public IssueLinkJiraBean setType(IssueLinkJiraTypeBean type) {
        this.type = type;
        return this;
    }

    public IssueJiraBean getInwardIssue() {
        return inwardIssue;
    }

    public IssueLinkJiraBean setInwardIssue(IssueJiraBean inwardIssue) {
        this.inwardIssue = inwardIssue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueLinkJiraBean that = (IssueLinkJiraBean) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(inwardIssue, that.inwardIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, inwardIssue);
    }

    @Override
    public String toString() {
        return "IssueLinkJiraBean{" +
                "type=" + type +
                ", inwardIssue=" + inwardIssue +
                '}';
    }
}
