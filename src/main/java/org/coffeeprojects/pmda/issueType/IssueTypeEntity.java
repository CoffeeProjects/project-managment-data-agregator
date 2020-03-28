package org.coffeeprojects.pmda.issueType;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "IssueType")
public class IssueTypeEntity extends AuditableEntity implements Serializable {
    @Id
    private String id;

    private String name;

    private String description;

    public String getId() {
        return id;
    }

    public IssueTypeEntity setId(String id) {
        this.id = id;
        return this;
    }

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
}
