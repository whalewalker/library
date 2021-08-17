package com.library.library.domain.repository;

import com.library.library.web.payloads.Token;
import lombok.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByToken(String token);
}
