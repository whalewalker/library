package com.library.library.domain.repository;

import com.library.library.domain.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByUsername(String username);

    Optional<Author> findByEmail(String email);

    Optional<Author> findByVerificationToken(String verificationToken);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<Author> findAllByUsername(String username);
}
