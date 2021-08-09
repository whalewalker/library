package com.library.library.domain.repository;

import com.library.library.domain.models.Role;
import com.library.library.domain.models.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRoleName(RoleName roleName);
}
