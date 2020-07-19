package org.coffeeprojects.pmda.feature.priority;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "priority")
public class PriorityEntity extends BaseEntity<PriorityEntity> implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public PriorityEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityEntity that = (PriorityEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PriorityEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
