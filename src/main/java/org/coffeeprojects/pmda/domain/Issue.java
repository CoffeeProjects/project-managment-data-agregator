package org.coffeeprojects.pmda.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Issue implements Serializable {
    @Id
    private String id;

    private String key;

    private User assignee;

    private User reporter;

    private User creator;

    private String summary;

    private Status status;

    private Resolution resolution;

    @Temporal(TemporalType.TIMESTAMP)
    private Date resolutionDate;

    private Priority priority;

    private IssueType issueType;

    private Project project;

    private Set<Version> fixVersions;

    private List<String> labels;

    private Set<Component> components;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @OneToMany
    private Set<Issue> issueLinks;
}
