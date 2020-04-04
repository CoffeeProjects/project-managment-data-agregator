package org.coffeeprojects.pmda.component;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.issue.IssueEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
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

    public void setIssues(Set<IssueEntity> issues) {
        this.issues = issues;
    }
}
