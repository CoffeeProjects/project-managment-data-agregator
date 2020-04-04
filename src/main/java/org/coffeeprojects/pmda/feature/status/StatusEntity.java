package org.coffeeprojects.pmda.feature.status;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "status")
public class StatusEntity extends BaseEntity implements Serializable {

    private String name;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private ProjectEntity project;

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

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}
