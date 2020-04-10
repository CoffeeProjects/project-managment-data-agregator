package org.coffeeprojects.pmda.feature.issueType;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

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
}
