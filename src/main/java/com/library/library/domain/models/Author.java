package com.library.library.domain.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
public class Author{
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String profession;

    @CreatedDate
    private LocalDateTime datePublished;

    @LastModifiedDate
    private LocalDateTime dateModified;

    @NotBlank
    @Size(min = 4, max = 50)
    private String username;

    @Email(message = "Email cannot be null")
    @Size(min = 8, max = 50)
    private String email;


    private String phoneNumber;

    private Address address;

    @DBRef
    private List<Post> posts;

    @DBRef
    private List<Book> books;

    @DBRef
    private List<Role> roles;




}
