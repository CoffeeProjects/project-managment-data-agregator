package org.coffeeprojects.pmda.component;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Component")
public class ComponentEntity extends AuditableEntity implements Serializable {
    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public ComponentEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ComponentEntity setName(String name) {
        this.name = name;
        return this;
    }
}
