package org.coffeeprojects.pmda.feature.component;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Component")
public class ComponentEntity extends BaseEntity implements Serializable {

    private String name;

    @ManyToMany(mappedBy="components")
    private Set<IssueEntity> issues;

    public String getName() {
        return name;
    }

    public ComponentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Set<IssueEntity> getIssues() {
        return issues;
    }

    public ComponentEntity setIssues(Set<IssueEntity> issues) {
        this.issues = issues;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComponentEntity that = (ComponentEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(issues, that.issues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, issues);
    }

    @Override
    public String toString() {
        return "ComponentEntity{" +
                "name='" + name + '\'' +
                ", issues=" + issues +
                '}';
    }
}
