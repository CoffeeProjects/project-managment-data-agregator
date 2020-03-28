package org.coffeeprojects.pmda.user;

public class UserJiraBean {

    private String accountId;

    private String emailAddress;

    private String displayName;

    private boolean active;

    public String getAccountId() {
        return accountId;
    }

    public UserJiraBean setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public UserJiraBean setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserJiraBean setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public UserJiraBean setActive(boolean active) {
        this.active = active;
        return this;
    }
}
