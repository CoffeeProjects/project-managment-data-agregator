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

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserJiraBean getAuthor() {
        return author;
    }

    public void setAuthor(UserJiraBean author) {
        this.author = author;
    }

    public Set<ItemHistoryJiraBean> getItems() {
        return items;
    }

    public void setItems(Set<ItemHistoryJiraBean> items) {
        this.items = items;
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