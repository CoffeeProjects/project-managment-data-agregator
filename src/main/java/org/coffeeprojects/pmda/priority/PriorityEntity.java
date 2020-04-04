package org.coffeeprojects.pmda.priority;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "priority")
public class PriorityEntity extends BaseEntity implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public PriorityEntity setName(String name) {
        this.name = name;
        return this;
    }
}
