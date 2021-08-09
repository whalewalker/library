package com.library.library.domain.repository;

import com.library.library.domain.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {
    Optional<Post> findByTitle(String title);
//    List<Post> findPostByAuthorUserName(String username);
    List<Post> findByOrderByDatePublishedDesc();
}
