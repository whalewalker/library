package com.library.library.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class BookDto {
    private String title;

    private String publisher;

    private String description;

    private MultipartFile coverImage;

    private MultipartFile bookUrl;

    @CreatedDate
    private LocalDateTime datePublished;

    @LastModifiedDate
    private LocalDateTime dateModified;
}
