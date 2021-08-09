package com.library.library.domain.models;


import ch.qos.logback.core.util.COWArrayList;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
@Data
public class Book {
    @Id
    private String id;

    @Indexed
    @NotBlank
    private String title;

    private String publisher;

    private String description;

    private String coverImage;

    private String bookUrl;


    @CreatedDate
    private LocalDateTime datePublished;

    @LastModifiedDate
    private LocalDateTime dateModified;

    @DBRef
    private Set<Author> authors = new HashSet<>();

    @DBRef
    private Set<Category> categories = new HashSet<>();

    @DBRef
    private List<Comment> comments = new ArrayList<>();

}
