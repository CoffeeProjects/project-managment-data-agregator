package org.coffeeprojects.pmda.entity;

import org.coffeeprojects.pmda.tracker.TrackerType;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositeIdBaseEntity implements Serializable {

    private String clientId;

    private TrackerType trackerType;

    private String trackerLocalId;

    public String getClientId() {
        return clientId;
    }

    public CompositeIdBaseEntity setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public TrackerType getTrackerType() {
        return trackerType;
    }

    public CompositeIdBaseEntity setTrackerType(TrackerType trackerType) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeIdBaseEntity that = (CompositeIdBaseEntity) o;
        return Objects.equals(clientId, that.clientId) &&
                trackerType == that.trackerType &&
                Objects.equals(trackerLocalId, that.trackerLocalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, trackerType, trackerLocalId);
    }

    @Override
    public String toString() {
        return "CompositeIdBaseEntity{" +
                "clientId='" + clientId + '\'' +
                ", trackerType=" + trackerType +
                ", trackerLocalId='" + trackerLocalId + '\'' +
                '}';
    }
}
