package org.coffeeprojects.pmda.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
}