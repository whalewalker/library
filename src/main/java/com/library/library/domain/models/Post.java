package com.library.library.domain.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Document
public class Post {
    @Id
    private String id;

    @Indexed
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String blogImage;

    @DBRef
    private Author author;

    @CreatedDate
    private LocalDateTime datePublished;

    @LastModifiedDate
    private LocalDateTime dateModified;

    @DBRef
    private List<Comment> comments;

    public void addComments(Comment... comments){
        if (this.comments == null)
            this.comments = new ArrayList<>();
        this.comments.addAll(Arrays.asList(comments));
    }


}
