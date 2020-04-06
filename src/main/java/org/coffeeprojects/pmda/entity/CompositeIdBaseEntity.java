package org.coffeeprojects.pmda.entity;

import org.coffeeprojects.pmda.feature.project.ProjectEnum;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeIdBaseEntity implements Serializable {

    private String storageId;

    private ProjectEnum trackerType;

    private String trackerId;

    public String getStorageId() {
        return storageId;
    }

    public CompositeIdBaseEntity setStorageId(String storageId) {
        this.storageId = storageId;
        return this;
    }

    public ProjectEnum getTrackerType() {
        return trackerType;
    }

    public CompositeIdBaseEntity setTrackerType(ProjectEnum trackerType) {
        this.trackerType = trackerType;
        return this;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public CompositeIdBaseEntity setTrackerId(String trackerId) {
        this.trackerId = trackerId;
        return this;
    }
}
