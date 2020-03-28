package org.coffeeprojects.pmda.status;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Status")
public class StatusEntity extends AuditableEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private String description;

    public String getId() {
        return id;
    }

    public StatusEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StatusEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StatusEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
