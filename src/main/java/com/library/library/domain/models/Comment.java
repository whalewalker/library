package com.library.library.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Comment {
    @Id
    private String id;

    @NotBlank
    private String content;
}
