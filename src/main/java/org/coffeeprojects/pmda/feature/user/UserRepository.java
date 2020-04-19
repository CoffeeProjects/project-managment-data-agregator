package org.coffeeprojects.pmda.feature.user;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, CompositeIdBaseEntity> {
}
