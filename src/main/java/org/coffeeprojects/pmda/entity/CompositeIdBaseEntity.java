package org.coffeeprojects.pmda.entity;

import org.coffeeprojects.pmda.feature.project.ProjectEnum;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeIdBaseEntity implements Serializable {

    private String clientId;

    private ProjectEnum trackerType;

    private String trackerLocalId;

    public String getClientId() {
        return clientId;
    }

    public CompositeIdBaseEntity setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public ProjectEnum getTrackerType() {
        return trackerType;
    }

    public CompositeIdBaseEntity setTrackerType(ProjectEnum trackerType) {
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
