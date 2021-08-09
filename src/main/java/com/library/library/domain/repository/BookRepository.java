package com.library.library.domain.repository;



import com.library.library.domain.models.Author;
import com.library.library.domain.models.Book;
import com.library.library.domain.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findBookByTitle(String title);
    List<Book> findBookByCategories(Category category);
    List<Book> findBookByAuthors(Author author);
    List<Book> findBookByPublisher(String publisher);
}
