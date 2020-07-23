package org.coffeeprojects.pmda.feature.component;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Component")
public class ComponentEntity extends BaseEntity<ComponentEntity> implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public ComponentEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComponentEntity that = (ComponentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ComponentEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
