package org.coffeeprojects.pmda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<T> implements Serializable  {

    @EmbeddedId
    private CompositeIdBaseEntity id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public CompositeIdBaseEntity getId() {
        return id;
    }

    public T setId(CompositeIdBaseEntity id) {
        this.id = id;
        return (T)this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public T setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return (T)this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public T setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return (T)this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
