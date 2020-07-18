package org.coffeeprojects.pmda.tracker;

import java.util.Objects;

public class TrackerParametersBean {
    TrackerType type;
    String localId;
    String clientId;
    Object client;

    public TrackerType getType() {
        return type;
    }

    public TrackerParametersBean setType(TrackerType type) {
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

    public TrackerParametersBean setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public Object getClient() {
        return client;
    }

    public TrackerParametersBean setClient(Object client) {
        this.client = client;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackerParametersBean that = (TrackerParametersBean) o;
        return type == that.type &&
                Objects.equals(localId, that.localId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, localId, clientId, client);
    }

    @Override
    public String toString() {
        return "TrackerParametersBean{" +
                "type=" + type +
                ", localId='" + localId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", client=" + client +
                '}';
    }
}
