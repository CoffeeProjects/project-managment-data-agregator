package org.coffeeprojects.pmda.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Issue")
public class IssueEntity implements Serializable {
    @Id
    private String id;

    private String key;

    private UserEntity assignee;

    private UserEntity reporter;

    private UserEntity creator;

    private String summary;

    private StatusEntity status;

    private ResolutionEntity resolution;

    @Temporal(TemporalType.TIMESTAMP)
    private Date resolutionDate;

    private PriorityEntity priority;

    private IssueTypeEntity issueType;

    private ProjectEntity project;

    private Set<VersionEntity> fixVersions;

    private List<String> labels;

    private Set<ComponentEntity> components;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @OneToMany
    private Set<IssueEntity> issueLinks;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;
}
