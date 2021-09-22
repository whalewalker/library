package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.models.Author;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.web.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Author> getAllUsers() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByUsername(String username) throws UsernameNotFoundException {
        return authorRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with this username %s does not exist", username)));
    }

    @Override
    public void deleteAuthor(String authorId) throws UserException {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new UserException(String.format("User with this id %s does not exist", authorId)));
        authorRepository.delete(author);
    }

    @Override
    public AuthorDto updateAuthorProfile(String authorId, AuthorDto authorDto) throws UserException {
        Author authorToUpdate = authorRepository.findById(authorId).orElseThrow(
                () -> new UserException(String.format("User with this id %s does not exist", authorId)));

        modelMapper.map(authorDto, authorToUpdate);
        Author updatedAuthor = authorRepository.save(authorToUpdate);
        return modelMapper.map(updatedAuthor, AuthorDto.class);
    }

    @Override
    public List<Author> getAllSearchPattern(String username) {
        return authorRepository.findAllByUsername(username);
    }
}
