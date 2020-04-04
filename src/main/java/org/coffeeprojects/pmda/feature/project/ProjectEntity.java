package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "project")
public class ProjectEntity extends BaseEntity implements Serializable {

    private String key;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCheck;

    @Column(insertable = false, updatable = false)
    private ProjectEnum trackerType;

    @Column(insertable = false, updatable = false)
    private Integer trackerNumber;

    @Column(insertable = false, updatable = false)
    private Boolean active;

    public String getKey() {
        return key;
    }

    public ProjectEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public ProjectEnum getTrackerType() {
        return trackerType;
    }

    public void setTrackerType(ProjectEnum trackerType) {
        this.trackerType = trackerType;
    }

    public Integer getTrackerNumber() {
        return trackerNumber;
    }

    public void setTrackerNumber(Integer trackerNumber) {
        this.trackerNumber = trackerNumber;
    }

    public Boolean isActive() {
        return active;
    }

    public ProjectEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
