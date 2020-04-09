package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, CompositeIdBaseEntity> {
}
