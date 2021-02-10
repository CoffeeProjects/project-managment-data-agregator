package org.coffeeprojects.pmda.feature.changelog.jirabean;

import java.util.Set;

public class ChangelogJiraBean {

    private Set<HistoryJiraBean> histories;

    public Set<HistoryJiraBean> getHistories() {
        return histories;
    }

    public ChangelogJiraBean setHistories(Set<HistoryJiraBean> histories) {
        this.histories = histories;
        return this;
    }

    @Override
    public String toString() {
        return "ChangelogJiraBean{" +
                "histories=" + histories +
                '}';
    }
}