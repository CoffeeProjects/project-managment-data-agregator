package org.coffeeprojects.pmda.tracker;

public class TrackerBean {

    private String type;
    private String id;
    private String url;
    private String user;
    private String password;

    public TrackerBean(String type, String id, String url, String user, String password) {
        this.type = type;
        this.id = id;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public TrackerBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}