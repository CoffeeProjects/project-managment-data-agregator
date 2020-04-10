package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "sprint")
public class SprintEntity extends BaseEntity implements Serializable {

    private String rapidViewId;

    private String state;

    private String name;

    private String goal;

    private Instant startDate;

    private Instant endDate;

    private Instant completeDate;

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

    public Instant getStartDate() {
        return startDate;
    }

    public SprintEntity setStartDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public SprintEntity setEndDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public Instant getCompleteDate() {
        return completeDate;
    }

    public SprintEntity setCompleteDate(Instant completeDate) {
        this.completeDate = completeDate;
        return this;
    }

    public Set<IssueEntity> getIssues() {
        return issues;
    }

    public SprintEntity setIssues(Set<IssueEntity> issues) {
        this.issues = issues;
        return this;
    }
}
