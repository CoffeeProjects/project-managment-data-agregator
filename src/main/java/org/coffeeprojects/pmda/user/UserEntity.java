package org.coffeeprojects.pmda.user;

import org.coffeeprojects.pmda.entity.AuditableEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "User")
public class UserEntity extends AuditableEntity implements Serializable {
    @Id
    private String accountId;

    private String emailAddress;

    private String displayName;

    private boolean active;

    public String getAccountId() {
        return accountId;
    }

    public UserEntity setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

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

    public boolean isActive() {
        return active;
    }

    public UserEntity setActive(boolean active) {
        this.active = active;
        return this;
    }
}
