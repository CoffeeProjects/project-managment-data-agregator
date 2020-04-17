package org.coffeeprojects.pmda.feature.issue.jirabean;

import java.util.List;
import java.util.Objects;

public class SearchIssuesResultJiraBean {
    private String expand;

    private Long startAt;

    private Long maxResults;

    private Long total;

    private List<IssueJiraBean> issues;

    public String getExpand() {
        return expand;
    }

    public SearchIssuesResultJiraBean setExpand(String expand) {
        this.expand = expand;
        return this;
    }

    public Long getStartAt() {
        return startAt;
    }

    public SearchIssuesResultJiraBean setStartAt(Long startAt) {
        this.startAt = startAt;
        return this;
    }

    public Long getMaxResults() {
        return maxResults;
    }

    public SearchIssuesResultJiraBean setMaxResults(Long maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public SearchIssuesResultJiraBean setTotal(Long total) {
        this.total = total;
        return this;
    }

    public List<IssueJiraBean> getIssues() {
        return issues;
    }

    public SearchIssuesResultJiraBean setIssues(List<IssueJiraBean> issues) {
        this.issues = issues;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchIssuesResultJiraBean that = (SearchIssuesResultJiraBean) o;
        return Objects.equals(expand, that.expand) &&
                Objects.equals(startAt, that.startAt) &&
                Objects.equals(maxResults, that.maxResults) &&
                Objects.equals(total, that.total) &&
                Objects.equals(issues, that.issues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expand, startAt, maxResults, total, issues);
    }

    @Override
    public String toString() {
        return "SearchIssuesResultJiraBean{" +
                "expand='" + expand + '\'' +
                ", startAt=" + startAt +
                ", maxResults=" + maxResults +
                ", total=" + total +
                ", issues=" + issues +
                '}';
    }
}
