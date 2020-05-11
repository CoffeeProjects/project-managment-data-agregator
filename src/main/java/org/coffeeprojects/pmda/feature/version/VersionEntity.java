package org.coffeeprojects.pmda.feature.version;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "version")
public class VersionEntity extends BaseEntity implements Serializable {

    private String name;

    private String description;

    private Boolean archived;

    private Boolean released;

    private Instant releaseDate;

    public String getName() {
        return name;
    }

    public VersionEntity setName(String name) {
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

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VersionEntity that = (VersionEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(archived, that.archived) &&
                Objects.equals(released, that.released) &&
                Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, archived, released, releaseDate);
    }

    @Override
    public String toString() {
        return "VersionEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", archived=" + archived +
                ", released=" + released +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
