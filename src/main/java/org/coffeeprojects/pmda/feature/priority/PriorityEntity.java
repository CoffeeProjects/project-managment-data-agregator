package org.coffeeprojects.pmda.feature.priority;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "priority")
public class PriorityEntity extends BaseEntity implements Serializable {

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
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PriorityEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
