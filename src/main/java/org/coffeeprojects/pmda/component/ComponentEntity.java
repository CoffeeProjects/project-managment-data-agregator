package org.coffeeprojects.pmda.component;

import org.coffeeprojects.pmda.entity.AuditableEntity;
import org.coffeeprojects.pmda.issue.IssueEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Component")
public class ComponentEntity extends AuditableEntity implements Serializable {
    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy="components")
    private Set<IssueEntity> issues;

    public String getId() {
        return id;
    }

    public ComponentEntity setId(String id) {
        this.id = id;
        return this;
    }

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

    public void setIssues(Set<IssueEntity> issues) {
        this.issues = issues;
    }
}
