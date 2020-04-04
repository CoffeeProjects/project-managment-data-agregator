package org.coffeeprojects.pmda.resolution;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "resolution")
public class ResolutionEntity extends AuditableEntity implements Serializable {

    @Id
    private String id;

    private String name;

    private String description;

    public String getId() {
        return id;
    }

    public ResolutionEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResolutionEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ResolutionEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
