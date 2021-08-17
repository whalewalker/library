package com.library.library.domain.models;

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
public class Author{
    @Id
    private String id;

    private String fullName;

    private String profession;

    @CreatedDate
    private LocalDate datePublished;

    @LastModifiedDate
    private LocalDate dateModified;

    @NotBlank
    @Size(min = 4, max = 50)
    private String username;

    @Email(message = "Email cannot be null")
    @Size(min = 8, max = 50)
    private String email;

    @NotBlank
    private String password;

    private String phoneNumber;

    private Address address;

    @DBRef
    private List<Post> posts = new ArrayList<>();

    @DBRef
    private List<Book> books = new ArrayList<>();

    @DBRef
    private Set<Role> roles = new HashSet<>();


    public Author(String fullName, String username, String email, String password) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
