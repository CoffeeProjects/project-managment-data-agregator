package org.coffeeprojects.pmda.entity;

import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeIdBaseEntity implements Serializable {

    private String clientId;

    private TrackerTypeEnum trackerType;

    private String trackerLocalId;

    public String getClientId() {
        return clientId;
    }

    public CompositeIdBaseEntity setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public TrackerTypeEnum getTrackerType() {
        return trackerType;
    }

    public CompositeIdBaseEntity setTrackerType(TrackerTypeEnum trackerType) {
        this.trackerType = trackerType;
        return this;
    }

    public String getTrackerLocalId() {
        return trackerLocalId;
    }

    public CompositeIdBaseEntity setTrackerLocalId(String trackerLocalId) {
        this.trackerLocalId = trackerLocalId;
        return this;
    }
}
