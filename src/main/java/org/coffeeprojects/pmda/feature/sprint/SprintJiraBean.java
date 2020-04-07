package org.coffeeprojects.pmda.feature.sprint;

import java.util.Date;
import java.util.Objects;

public class SprintJiraBean {

    private String id;
    private String rapidViewId;
    private String state;
    private String name;
    private String goal;
    private Date startDate;
    private Date endDate;
    private Date completeDate;

    public String getId() {
        return id;
    }

    public SprintJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getRapidViewId() {
        return rapidViewId;
    }

    public SprintJiraBean setRapidViewId(String rapidViewId) {
        this.rapidViewId = rapidViewId;
        return this;
    }

    public String getState() {
        return state;
    }

    public SprintJiraBean setState(String state) {
        this.state = state;
        return this;
    }

    public String getName() {
        return name;
    }

    public SprintJiraBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getGoal() {
        return goal;
    }

    public SprintJiraBean setGoal(String goal) {
        this.goal = goal;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public SprintJiraBean setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public SprintJiraBean setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public SprintJiraBean setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintJiraBean that = (SprintJiraBean) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(rapidViewId, that.rapidViewId) &&
                Objects.equals(state, that.state) &&
                Objects.equals(name, that.name) &&
                Objects.equals(goal, that.goal) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(completeDate, that.completeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rapidViewId, state, name, goal, startDate, endDate, completeDate);
    }

    @Override
    public String toString() {
        return "SprintJiraBean{" +
                "id='" + id + '\'' +
                ", rapidViewId='" + rapidViewId + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", goal='" + goal + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completeDate=" + completeDate +
                '}';
    }
}
