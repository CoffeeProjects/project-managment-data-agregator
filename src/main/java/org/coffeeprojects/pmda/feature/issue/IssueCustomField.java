package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectCustomField;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IssueCustomField that = (IssueCustomField) o;
        return Objects.equals(projectCustomFields, that.projectCustomFields) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projectCustomFields, value);
    }

    @Override
    public String toString() {
        return "IssueCustomField{" +
                "projectCustomFields=" + projectCustomFields +
                ", value='" + value + '\'' +
                '}';
    }
}