package com.library.library.domain.repository;

import com.library.library.domain.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByUsername(String username);
    Optional<Author> findByEmail(String email);
    Optional<Author> findByVerificationToken(String verificationToken);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
