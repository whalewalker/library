package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.models.Author;
import com.library.library.domain.models.Role;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.web.exceptions.AuthUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthServiceImpl authService;
    private Author authorTest;

    @BeforeEach
    void setUp() {
        authorRepository = mock(AuthorRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);

        authorTest = new Author("sam", "sma@gmail.com", "password");
        authorTest.getRoles().add(Role.USER);
        authorTest.setVerificationToken(UUID.randomUUID().toString());
        authService = new AuthServiceImpl(authorRepository, passwordEncoder);
    }


    @Test
    void whenRegisterUserMethodIsCalled_theUserRepositorySaveMethodIsCalledOnce() throws AuthUserException {
        AuthorDto registerDto = new AuthorDto("sam", "sma@gmail.com", "password");
        when(authorRepository.save(any(Author.class))).thenReturn(authorTest);
        Author savedAuthor = authService.registerUser(registerDto);
        assertThat(savedAuthor).isNotNull();
        verify(authorRepository).existsByEmail(registerDto.getEmail());
        verify(authorRepository).existsByUsername(registerDto.getUsername());
        verify(authorRepository).save(new Author());
    }
}