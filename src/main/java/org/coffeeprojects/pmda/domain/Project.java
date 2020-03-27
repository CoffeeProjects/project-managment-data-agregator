package org.coffeeprojects.pmda.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    @Id
    private String id;

    private String key;

    private String name;

    private String projectTypeKey;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCheck;

    private boolean active;
}