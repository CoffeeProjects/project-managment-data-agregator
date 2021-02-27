package org.coffeeprojects.pmda.feature.changelog;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "changelog")
public class ChangelogEntity extends BaseEntity<ChangelogEntity> implements Serializable {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private UserEntity author;

    private String field;

    private String fieldType;

    private String fieldId;

    @Column(columnDefinition="TEXT")
    private String fromString;

    @Column(columnDefinition="TEXT")
    private String toString;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public UserEntity getAuthor() {
        return author;
    }

    public ChangelogEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getField() {
        return field;
    }

    public ChangelogEntity setField(String field) {
        this.field = field;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public ChangelogEntity setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public String getFieldId() {
        return fieldId;
    }

    public ChangelogEntity setFieldId(String fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public String getFromString() {
        return fromString;
    }

    public ChangelogEntity setFromString(String fromString) {
        this.fromString = fromString;
        return this;
    }

    public String getToString() {
        return toString;
    }

    public ChangelogEntity setToString(String toString) {
        this.toString = toString;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public ChangelogEntity setCreated(Date created) {
        this.created = created;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChangelogEntity that = (ChangelogEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(author, that.author) && Objects.equals(field, that.field) && Objects.equals(fieldType, that.fieldType) && Objects.equals(fieldId, that.fieldId) && Objects.equals(fromString, that.fromString) && Objects.equals(toString, that.toString) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, field, fieldType, fieldId, fromString, toString, created);
    }

    @Override
    public String toString() {
        return "ChangelogEntity{" +
                "id=" + super.toString() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", author=" + author +
                ", field='" + field + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", fromString='" + fromString + '\'' +
                ", toString='" + toString + '\'' +
                ", created=" + created +
                '}';
    }
}