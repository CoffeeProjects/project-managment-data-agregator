package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "issue_custom_field")
public class IssueCustomField extends BaseEntity implements Serializable {

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private ProjectCustomField projectCustomFields;

    private String value;

    public ProjectCustomField getProjectCustomFields() {
        return projectCustomFields;
    }

    public IssueCustomField setProjectCustomFields(ProjectCustomField projectCustomFields) {
        this.projectCustomFields = projectCustomFields;
        return this;
    }

    public String getValue() {
        return value;
    }

    public IssueCustomField setValue(String value) {
        this.value = value;
        return this;
    }
}