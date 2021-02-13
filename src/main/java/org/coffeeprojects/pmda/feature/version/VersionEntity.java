package org.coffeeprojects.pmda.feature.version;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "version")
public class VersionEntity extends BaseEntity<VersionEntity> implements Serializable {

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

    public VersionEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getArchived() {
        return archived;
    }

    public VersionEntity setArchived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public Boolean getReleased() {
        return released;
    }

    public VersionEntity setReleased(Boolean released) {
        this.released = released;
        return this;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public VersionEntity setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VersionEntity that = (VersionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VersionEntity{" +
                "id=" + super.toString() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", archived=" + archived +
                ", released=" + released +
                ", releaseDate=" + releaseDate +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
