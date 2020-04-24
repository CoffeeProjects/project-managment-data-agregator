package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintEntity that = (SprintEntity) o;
        return Objects.equals(rapidViewId, that.rapidViewId) &&
                Objects.equals(state, that.state) &&
                Objects.equals(name, that.name) &&
                Objects.equals(goal, that.goal) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(completeDate, that.completeDate) &&
                Objects.equals(issues, that.issues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rapidViewId, state, name, goal, startDate, endDate, completeDate, issues);
    }

    @Override
    public String toString() {
        return "SprintEntity{" +
                "id='" + getId().toString() + '\'' +
                ", rapidViewId='" + rapidViewId + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", goal='" + goal + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completeDate=" + completeDate +
                ", issues=" + issues +
                '}';
    }
}
