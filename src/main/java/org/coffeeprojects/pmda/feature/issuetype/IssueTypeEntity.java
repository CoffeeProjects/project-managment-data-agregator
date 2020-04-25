package org.coffeeprojects.pmda.feature.issuetype;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "issue_type")
public class IssueTypeEntity extends BaseEntity implements Serializable {

    private String name;

    private String description;

    @OneToOne
    ProjectCustomField projectCustomField;

    public String getName() {
        return name;
    }

    public IssueTypeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueTypeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueTypeEntity that = (IssueTypeEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(projectCustomField, that.projectCustomField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, projectCustomField);
    }

    @Override
    public String toString() {
        return "IssueTypeEntity{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectCustomField=" + projectCustomField +
                '}';
    }
}
