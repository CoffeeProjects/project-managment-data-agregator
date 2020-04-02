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

    @Column(insertable = false, updatable = false)
    private ProjectEnum type;

    @Column(insertable = false, updatable = false)
    private Boolean active;

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

    public ProjectEnum getType() {
        return type;
    }

    public void setType(ProjectEnum type) {
        this.type = type;
    }

    public Boolean isActive() {
        return active;
    }

    public ProjectEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
