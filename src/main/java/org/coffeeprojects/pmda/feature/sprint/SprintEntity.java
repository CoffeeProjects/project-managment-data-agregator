package org.coffeeprojects.pmda.feature.sprint;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "sprint")
public class SprintEntity extends BaseEntity<SprintEntity> implements Serializable {

    private String rapidViewId;

    private String state;

    private String name;

    @Column(length = 500)
    private String goal;

    private Instant startDate;

    private Instant endDate;

    private Instant completeDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintEntity that = (SprintEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SprintEntity{" +
                "id='" + getId() + '\'' +
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
