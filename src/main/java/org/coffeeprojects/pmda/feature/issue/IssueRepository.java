package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<IssueEntity, CompositeIdBaseEntity> {
    List<IssueEntity> findByProjectAndResolutionDateIsNull(ProjectEntity projectEntity);
}
