package com.library.library.domain.repository;

import com.library.library.domain.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByUsernameOrEmail(String username, String email);
}
