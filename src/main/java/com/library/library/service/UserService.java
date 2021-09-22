package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.models.Author;
import com.library.library.web.exceptions.UserException;

import java.util.List;

public interface UserService {
    List<Author> getAllUsers();

    Author getAuthorByUsername(String username);

    void deleteAuthor(String authorId) throws UserException;

    AuthorDto updateAuthorProfile(String authorId, AuthorDto authorDto) throws UserException;

    List<Author> getAllSearchPattern(String username);

}
