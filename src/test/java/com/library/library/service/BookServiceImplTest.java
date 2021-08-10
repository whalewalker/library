package com.library.library.service;

import com.library.library.domain.dto.BookDto;
import com.library.library.domain.models.Book;
import com.library.library.domain.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    Book bookTest;

    @BeforeEach
    void setup(){
        bookTest = new Book();
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown(){
        bookTest = null;
    }

    @Test
    void whenTheSaveMethodISCalled_ThenRepositoryISCalledOnce(){
        when(bookService.uploadBook(new BookDto())).thenReturn(bookTest);
        bookService.uploadBook(new BookDto());

        verify(bookRepository, times(1)).save(bookTest);
    }

}