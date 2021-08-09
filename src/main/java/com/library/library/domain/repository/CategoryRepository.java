package com.library.library.domain.repository;

import com.library.library.domain.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<String, Category> {
}
