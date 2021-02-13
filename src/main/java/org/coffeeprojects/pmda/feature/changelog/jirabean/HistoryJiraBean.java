package org.coffeeprojects.pmda.feature.changelog.jirabean;

import org.coffeeprojects.pmda.feature.user.UserJiraBean;

import java.util.Date;
import java.util.Set;

public class HistoryJiraBean {

    private String id;

    private UserJiraBean author;

    private Date created;

    private Set<ItemHistoryJiraBean> items;

    public String getId() {
        return id;
    }

    public HistoryJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public HistoryJiraBean setCreated(Date created) {
        this.created = created;
        return this;
    }

    public UserJiraBean getAuthor() {
        return author;
    }

    public HistoryJiraBean setAuthor(UserJiraBean author) {
        this.author = author;
        return this;
    }

    public Set<ItemHistoryJiraBean> getItems() {
        return items;
    }

    public HistoryJiraBean setItems(Set<ItemHistoryJiraBean> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "HistoryJiraBean{" +
                "id='" + id + '\'' +
                ", author=" + author +
                ", created=" + created +
                ", items=" + items +
                '}';
    }
}