package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.models.Author;

import java.util.List;

public interface UserService {
    List<Author> getAllUsers();

    Author getAuthorByUsername(String username);

    void deleteAuthor(String authorId);

    AuthorDto updateAuthorProfile(String authorId, AuthorDto authorDto);

    List<Author> getAllSearchPattern(String username);

}
