package com.library.library.web.controllers;


import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.models.Author;
import com.library.library.service.UserService;
import com.library.library.web.exceptions.UserException;
import com.library.library.web.payloads.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/users")

public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("user/{username}")
    public ResponseEntity<?> getUserWithUsername(@PathVariable String username) {
        try {
            Author author = userService.getAuthorByUsername(username);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllUserWithUsername(@PathVariable String username) {
        List<Author> authors = userService.getAllSearchPattern(username);
        if (authors == null) {
            return new ResponseEntity<>(new ApiResponse(false, String.format("No user found with username %s", username)), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        try {
            userService.deleteAuthor(userId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted successfully"), HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> updateUserDetails(@PathVariable String userId, AuthorDto authorDto) {
        try {
            AuthorDto updateAuthor = userService.updateAuthorProfile(userId, authorDto);
            return new ResponseEntity<>(updateAuthor, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<Author> authors = userService.getAllUsers();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
