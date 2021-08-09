package com.library.library.domain.repository;

import com.library.library.domain.models.Author;
import com.library.library.domain.models.Book;
import com.library.library.domain.models.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookRepositoryTest {
    Book book;
    Category category;
    Author author;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryName("fantasy");

        author = new Author();
        author.setUsername("Abdul");
        author.setEmail("aboul@gmail.com");


        book = new Book();
        book.setTitle("Computer software");
        book.setDescription("Computer software are program that runs on computer");
        book.setPublisher(author.getUsername());
        book.setBookUrl("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/Spring Boot Up and Running Building Cloud Native Java and Kotlin Applications by Mark Heckler [Mark Heckler] (z-lib.org).pdf");
    }

    @AfterEach
    void tearDown() {
        book = null;
        category = null;
        author = null;
    }

    @Test
    void canSaveBookToDataBase(){
        log.info("Created a book --> {}", book);
        Book bookSaved = bookRepository.save(book);
        assertThat(bookSaved).isNotNull();
        assertThat(bookSaved.getBookUrl()).isEqualTo(book.getBookUrl());
        assertThat(bookSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("When book is created then author is created")
    void createAuthor(){
        log.info("Created a book --> {}", book);
        authorRepository.save(author);
        book.getAuthors().add(author);
        Book bookSaved = bookRepository.save(book);
        assertThat(bookSaved).isNotNull();
        assertThat(bookSaved.getAuthors().size()).isEqualTo(1);
    }



    @Test
    @DisplayName("When book is created then category is created or added")
    void createCategory(){
        log.info("Created a book --> {}", book);
        categoryRepository.save(category);
        book.getCategories().add(category);
        Book bookSaved = bookRepository.save(book);
        assertThat(bookSaved).isNotNull();
        assertThat(bookSaved.getCategories().size()).isEqualTo(1);
    }

    @Test
    void findAllBooksInDatabaseTest(){
        Book bookSaved = bookRepository.save(book);
        assertThat(bookSaved).isNotNull();

        List<Book> existingBooks = bookRepository.findAll();
        assertThat(existingBooks).isNotNull();
        assertThat(existingBooks.size()).isEqualTo(1);
    }



}