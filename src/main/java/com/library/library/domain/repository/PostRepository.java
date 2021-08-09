package com.library.library.domain.repository;

import com.library.library.domain.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<String, Post> {
}
