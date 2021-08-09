package com.library.library.service;

import com.library.library.domain.dto.BookDto;
import com.library.library.domain.models.Book;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.domain.repository.BookRepository;
import com.library.library.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Optional<Book> getBookByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book saveBook(BookDto bookDto) {
        return null;
    }

    @Override
    public Book updateBook(String bookId, BookDto bookDto) {
        return null;
    }

    @Override
    public Book deleteBookById(String bookId) {
        return null;
    }

    @Override
    public List<Book> getBookByAuthorName(String authorName) {
        return null;
    }

    @Override
    public List<Book> getBookByCategory(String categoryName) {
        return null;
    }
}
