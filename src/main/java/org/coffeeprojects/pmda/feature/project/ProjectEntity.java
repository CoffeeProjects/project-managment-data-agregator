package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "project")
public class ProjectEntity extends BaseEntity<ProjectEntity> implements Serializable {

    private String key;

    private String name;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private UserEntity administrator;

    private Instant lastCheck;

    private Instant lastFailureDate;

    @Column(columnDefinition="TEXT")
    private String lastFailureMessage;

    private Integer failureCounter;

    private Boolean active;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "project_client_id", referencedColumnName = "clientId")
    @JoinColumn(name = "project_tracker_local_id", referencedColumnName = "trackerLocalId")
    @JoinColumn(name = "project_tracker_type", referencedColumnName = "trackerType")
    private Set<ProjectCustomField> projectCustomFields;

    public String getKey() {
        return key;
    }

    public ProjectEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity getAdministrator() {
        return administrator;
    }

    public ProjectEntity setAdministrator(UserEntity administrator) {
        this.administrator = administrator;
        return this;
    }

    public Instant getLastCheck() {
        return lastCheck;
    }

    public ProjectEntity setLastCheck(Instant lastCheck) {
        this.lastCheck = lastCheck;
        return this;
    }

    public Instant getLastFailureDate() {
        return lastFailureDate;
    }

    public void setLastFailureDate(Instant lastFailureDate) {
        this.lastFailureDate = lastFailureDate;
    }

    public String getLastFailureMessage() {
        return lastFailureMessage;
    }

    public void setLastFailureMessage(String lastFailureMessage) {
        this.lastFailureMessage = lastFailureMessage;
    }

    public Integer getFailureCounter() {
        return failureCounter;
    }

    public void setFailureCounter(Integer failureCounter) {
        this.failureCounter = failureCounter;
    }

    public Boolean isActive() {
        return active;
    }

    public ProjectEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Set<ProjectCustomField> getProjectCustomFields() {
        return projectCustomFields;
    }

    public ProjectEntity setProjectCustomFields(Set<ProjectCustomField> projectCustomFields) {
        this.projectCustomFields = projectCustomFields;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", administrator=" + administrator +
                ", lastCheck=" + lastCheck +
                ", lastFailureDate=" + lastFailureDate +
                ", lastFailureMessage='" + lastFailureMessage + '\'' +
                ", failureCounter=" + failureCounter +
                ", active=" + active +
                ", projectCustomFields=" + projectCustomFields +
                '}';
    }
}
