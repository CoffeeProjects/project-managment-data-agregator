package org.coffeeprojects.pmda.feature.version;

import java.util.Date;
import java.util.Objects;

public class VersionJiraBean {

    private String id;

    private String name;

    private String description;

    private Boolean archived;

    private Boolean released;

    private Date releaseDate;

    public String getId() {
        return id;
    }

    public VersionJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VersionJiraBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getReleased() {
        return released;
    }

    public void setReleased(Boolean released) {
        this.released = released;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionJiraBean that = (VersionJiraBean) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(archived, that.archived) &&
                Objects.equals(released, that.released) &&
                Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, archived, released, releaseDate);
    }

    @Override
    public String toString() {
        return "VersionJiraBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", archived=" + archived +
                ", released=" + released +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
