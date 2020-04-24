package org.coffeeprojects.pmda.feature.status;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "status")
public class StatusEntity extends BaseEntity implements Serializable {

    private String name;

    private String description;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
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

    public StatusEntity setProject(ProjectEntity project) {
        this.project = project;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, project);
    }

    @Override
    public String toString() {
        return "StatusEntity{" +
                "id='" + getId().toString() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", project=" + project +
                '}';
    }
}
