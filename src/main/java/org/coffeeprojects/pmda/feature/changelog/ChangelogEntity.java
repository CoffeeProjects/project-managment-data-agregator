package org.coffeeprojects.pmda.feature.changelog;

import org.coffeeprojects.pmda.entity.BaseEntity;
import org.coffeeprojects.pmda.feature.component.ComponentEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "changelog")
public class ChangelogEntity extends BaseEntity<ComponentEntity> implements Serializable {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private UserEntity author;

    private String field;

    private String fieldType;

    private String fieldId;

    private String from;

    private String fromString;

    private String to;

    private String toString;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromString() {
        return fromString;
    }

    public void setFromString(String fromString) {
        this.fromString = fromString;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChangelogEntity that = (ChangelogEntity) o;
        return Objects.equals(author, that.author) && Objects.equals(field, that.field) && Objects.equals(fieldType, that.fieldType) && Objects.equals(fieldId, that.fieldId) && Objects.equals(from, that.from) && Objects.equals(fromString, that.fromString) && Objects.equals(to, that.to) && Objects.equals(toString, that.toString) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, field, fieldType, fieldId, from, fromString, to, toString, created);
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
                ", from='" + from + '\'' +
                ", fromString='" + fromString + '\'' +
                ", to='" + to + '\'' +
                ", toString='" + toString + '\'' +
                ", created=" + created +
                '}';
    }
}
