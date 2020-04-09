package org.coffeeprojects.pmda.tracker;

import java.util.Objects;

public class TrackerDataBean {
    private String type;
    private String localId;
    private String clientId;
    private String url;
    private String user;
    private String password;

    public TrackerDataBean(String type, String localId, String clientId, String url, String user, String password) {
        this.type = type;
        this.localId = localId;
        this.clientId = clientId;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public TrackerDataBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocalId() {
        return localId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackerDataBean that = (TrackerDataBean) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(localId, that.localId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(url, that.url) &&
                Objects.equals(user, that.user) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, localId, clientId, url, user, password);
    }

    @Override
    public String toString() {
        return "TrackerDataBean{" +
                "type='" + type + '\'' +
                ", localId='" + localId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
