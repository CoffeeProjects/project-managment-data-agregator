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

    public SprintEntity setRapidViewId(String rapidViewId) {
        this.rapidViewId = rapidViewId;
        return this;
    }

    public String getState() {
        return state;
    }

    public SprintEntity setState(String state) {
        this.state = state;
        return this;
    }

    public String getName() {
        return name;
    }

    public SprintEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getGoal() {
        return goal;
    }

    public SprintEntity setGoal(String goal) {
        this.goal = goal;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public SprintEntity setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public SprintEntity setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public SprintEntity setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
        return this;
    }
}