package org.coffeeprojects.pmda.resolution;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "resolution")
public class ResolutionEntity extends BaseEntity implements Serializable {

    private String name;

    private String description;

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
