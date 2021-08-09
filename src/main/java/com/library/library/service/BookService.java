package com.library.library.service;

import com.library.library.domain.dto.BookDto;
import com.library.library.domain.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getBookByTitle(String title);
    List<Book> getAllBooks();
    Book saveBook(BookDto bookDto);
    Book updateBook(String bookId, BookDto bookDto);
    Book deleteBookById(String bookId);
    List<Book> getBookByAuthorName(String authorName);
    List<Book> getBookByCategory(String categoryName);
}
