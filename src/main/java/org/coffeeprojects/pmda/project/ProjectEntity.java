package org.coffeeprojects.pmda.project;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Project")
public class ProjectEntity extends AuditableEntity implements Serializable {
    @Id
    private String id;

    private String key;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCheck;

    private boolean active;

    public String getId() {
        return id;
    }

    public ProjectEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ProjectEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public ProjectEntity setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ProjectEntity setActive(boolean active) {
        this.active = active;
        return this;
    }
}
