package org.coffeeprojects.pmda.feature.version;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.issue.IssueEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "version")
public class VersionEntity extends BaseEntity implements Serializable {

    private String name;

    @ManyToMany(mappedBy="fixVersions")
    private Set<IssueEntity> issues;

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

    public VersionEntity setIssues(Set<IssueEntity> issues) {
        this.issues = issues;
        return this;
    }
}