package org.coffeeprojects.pmda.entity;

import org.coffeeprojects.pmda.feature.project.ProjectEnum;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeIdBaseEntity implements Serializable {

    private String storageId;

    private ProjectEnum trackerType;

    private Integer trackerId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public ProjectEnum getTrackerType() {
        return trackerType;
    }

    public void setTrackerType(ProjectEnum trackerType) {
        this.trackerType = trackerType;
    }

    public Integer getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Integer trackerId) {
        this.trackerId = trackerId;
    }
}
