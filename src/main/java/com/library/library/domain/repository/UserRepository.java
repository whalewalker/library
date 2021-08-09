package com.library.library.domain.repository;

import com.library.library.domain.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<String, User> {
}
