package com.library.library.domain.repository;

import com.library.library.domain.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<String, Role> {
}
