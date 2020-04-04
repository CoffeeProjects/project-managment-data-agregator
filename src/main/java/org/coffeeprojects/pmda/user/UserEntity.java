package org.coffeeprojects.pmda.user;

import org.coffeeprojects.pmda.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class UserEntity extends BaseEntity implements Serializable {

    private String emailAddress;

    private String displayName;

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

    public Boolean isActive() {
        return active;
    }

    public UserEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
