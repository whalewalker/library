package com.library.library.domain.repository;

import com.library.library.domain.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<String, Comment> {
}
