package org.coffeeprojects.pmda.sprint;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getRapidViewId() {
        return rapidViewId;
    }

    public void setRapidViewId(String rapidViewId) {
        this.rapidViewId = rapidViewId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
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
