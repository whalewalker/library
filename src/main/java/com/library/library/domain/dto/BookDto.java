package com.library.library.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.File;

@Data
@RequiredArgsConstructor
public class BookDto {
    @NotBlank(message = "title cannot be null")
    private String title;

    @NotBlank(message = "publisher cannot be blank")
    private String publisher;

    @NotBlank(message = "description cannot be blank")
    private String description;
    private MultipartFile pdfMultiPartFile;
    private File pdfFile;


    public BookDto(String title, String publisher, String description) {
        this.title = title;
        this.publisher = publisher;
        this.description = description;
    }

    //    private MultipartFile bookUrl;

}
