package org.coffeeprojects.pmda.version;

import org.coffeeprojects.pmda.entity.AuditableEntity;
import org.coffeeprojects.pmda.issue.IssueEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "version")
public class VersionEntity extends AuditableEntity implements Serializable {

    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy="fixVersions")
    private Set<IssueEntity> issues;

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

    public Set<IssueEntity> getIssues() {
        return issues;
    }

    public void setIssues(Set<IssueEntity> issues) {
        this.issues = issues;
    }
}
