package org.coffeeprojects.pmda.issue.jirabean;

import java.util.List;

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
}
