package org.coffeeprojects.pmda.feature.user;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "account")
public class UserEntity extends BaseEntity<UserEntity> implements Serializable {

    private String emailAddress;

    private String displayName;

    private String timeZone;

    private Boolean active;

    public String getEmailAddress() {
        return emailAddress;
    }

    public UserEntity setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserEntity setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public UserEntity setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public Boolean isActive() {
        return active;
    }

    public UserEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + super.toString() +
                ", emailAddress='" + emailAddress + '\'' +
                ", displayName='" + displayName + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", active=" + active +
                '}';
    }
}
