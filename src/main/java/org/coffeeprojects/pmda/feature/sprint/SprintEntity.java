package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sprint")
public class SprintEntity extends BaseEntity implements Serializable {

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

    @ManyToMany(mappedBy="sprints")
    private Set<IssueEntity> issues;

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

    public Set<IssueEntity> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueEntity> issues) {
        this.issues = issues;
    }
}
