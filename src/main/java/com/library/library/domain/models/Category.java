package com.library.library.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Category {
    @Id
    private String id;

    private String categoryName;

    private List<Book> bookCategory;

    private List<Post> postCategory;

    private List<Author> authorCategory;

}
