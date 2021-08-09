package com.library.library.domain.repository;

import com.library.library.domain.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<String, Book> {
}
