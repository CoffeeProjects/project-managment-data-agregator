package org.coffeeprojects.pmda.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class User implements Serializable {
    @Id
    private String accountId;

    private String emailAddress;

    private String displayName;

    private boolean active;
}
