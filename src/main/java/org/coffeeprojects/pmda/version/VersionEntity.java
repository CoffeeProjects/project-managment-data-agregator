package org.coffeeprojects.pmda.version;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Version")
public class VersionEntity extends AuditableEntity implements Serializable {
    @Id
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public VersionEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VersionEntity setName(String name) {
        this.name = name;
        return this;
    }
}
