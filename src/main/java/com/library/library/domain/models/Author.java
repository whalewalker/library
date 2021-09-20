package com.library.library.domain.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    private String id;
    private boolean isActive;
    private String verificationToken;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    @DBRef
    private List<Book> books = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();
    @CreatedDate
    private LocalDate datePublished;
    @LastModifiedDate
    private LocalDate dateModified;

    public Author(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
