package org.coffeeprojects.pmda.tracker;

public class TrackerParametersBean {
    TrackerTypeEnum type;
    String localId;
    String clientId;
    Object client;

    public TrackerTypeEnum getType() {
        return type;
    }

    public TrackerParametersBean setType(TrackerTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getLocalId() {
        return localId;
    }

    public TrackerParametersBean setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Object getClient() {
        return client;
    }

    public TrackerParametersBean setClient(Object client) {
        this.client = client;
        return this;
    }
}
