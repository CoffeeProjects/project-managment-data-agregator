package org.coffeeprojects.pmda.tracker;

public class Tracker {
    TrackerTypeEnum type;
    String localId;
    Object client;

    public TrackerTypeEnum getType() {
        return type;
    }

    public Tracker setType(TrackerTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getLocalId() {
        return localId;
    }

    public Tracker setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    public Object getClient() {
        return client;
    }

    public Tracker setClient(Object client) {
        this.client = client;
        return this;
    }
}
