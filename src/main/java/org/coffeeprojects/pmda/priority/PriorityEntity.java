package org.coffeeprojects.pmda.priority;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "priority")
public class PriorityEntity extends AuditableEntity implements Serializable {

    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public PriorityEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PriorityEntity setName(String name) {
        this.name = name;
        return this;
    }
}
