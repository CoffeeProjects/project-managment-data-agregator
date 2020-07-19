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
    public String toString() {
        return "ProjectCustomField{" +
                "localName='" + localName + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
