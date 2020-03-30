package org.coffeeprojects.pmda.sprint;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Sprint")
public class SprintEntity extends AuditableEntity implements Serializable {

    @Id
    private String id;
    private String rapidViewId;
    private String state;
    private String name;
    private String goal;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date completeDate;

    public String getId() {
        return id;
    }

    public SprintEntity setId(String id) {
        this.id = id;
        return this;
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
}
