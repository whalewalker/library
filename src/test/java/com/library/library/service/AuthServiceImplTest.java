package com.library.library.service;

import com.library.library.domain.dto.RegisterDto;
import com.library.library.domain.models.Author;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.web.exceptions.AuthUserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AuthServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    RegisterDto registerDto;

    @BeforeEach
    void setUp() {
        registerDto = new RegisterDto("Ismail", "sam", "sma@gmail.com", "passwors");
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
//    void AuthorCanBeCreatedTest() throws AuthUserException {
//        Author author = new Author();
//
//      when(authService.register(registerDto)).then((Answer<?>) registerDto);
//      authService.register(registerDto);
//
//      verify(authorRepository, times(1)).save(author);
//
//    }
}