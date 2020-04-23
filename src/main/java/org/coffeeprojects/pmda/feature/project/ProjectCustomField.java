package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "project_custom_field")
public class ProjectCustomField extends BaseEntity implements Serializable {

    String localName;

    String clientName;

    public String getLocalName() {
        return localName;
    }

    public ProjectCustomField setLocalName(String localName) {
        this.localName = localName;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public ProjectCustomField setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjectCustomField that = (ProjectCustomField) o;
        return Objects.equals(localName, that.localName) &&
                Objects.equals(clientName, that.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), localName, clientName);
    }

    @Override
    public String toString() {
        return "ProjectCustomField{" +
                "localName='" + localName + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}