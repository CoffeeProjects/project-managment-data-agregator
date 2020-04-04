package org.coffeeprojects.pmda.issueType;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "issue_type")
public class IssueTypeEntity extends BaseEntity implements Serializable {

    private String name;

    private String description;

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
