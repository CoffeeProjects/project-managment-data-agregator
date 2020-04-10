package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "project")
public class ProjectEntity extends BaseEntity implements Serializable {

    private String key;

    private String name;

    private Instant lastCheck;

    @Column(insertable = false, updatable = false)
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(name = "project_client_id"),
            @JoinColumn(name = "project_tracker_local_id"),
            @JoinColumn(name = "project_tracker_type")})
    private Set<ProjectCustomField> projectCustomFields;

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

    public Instant getLastCheck() {
        return lastCheck;
    }

    public ProjectEntity setLastCheck(Instant lastCheck) {
        this.lastCheck = lastCheck;
        return this;
    }

    public Boolean isActive() {
        return active;
    }

    public ProjectEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Set<ProjectCustomField> getProjectCustomFields() {
        return projectCustomFields;
    }

    public ProjectEntity setProjectCustomFields(Set<ProjectCustomField> projectCustomFields) {
        this.projectCustomFields = projectCustomFields;
        return this;
    }
}
