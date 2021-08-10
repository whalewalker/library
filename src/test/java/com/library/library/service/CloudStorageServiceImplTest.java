package com.library.library.service;

import com.cloudinary.utils.ObjectUtils;
import com.library.library.domain.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Slf4j
class CloudStorageServiceImplTest {

    @Autowired
    CloudStorageService cloudStorageServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void uploadImageWithMultipartFileTest(){
        File file = new File("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/me.jpeg");
        assertThat(file.exists()).isTrue();
        Map<Object, Object> params = new HashMap<>();

        params.put("public_id", "library/booK_image");
        params.put("overite", true);

        try {
            cloudStorageServiceImpl.uploadImage(file, params);
        } catch (IOException e) {
            log.info("Error occurred --> {}", e.getMessage());
        }

    }

    @Test
    @DisplayName("Uplaod multipart file test")
    void uploadImageTest() throws IOException {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Springboot");
        bookDto.setDescription("Springboot for beginner");

        Path path = Paths.get("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/me.jpeg");
        MultipartFile multipartFile = new MockMultipartFile("book_img", "book_img", "img/jpg", Files.readAllBytes(path));

        log.info("Multipart Object is created --> {}", multipartFile);
        assertThat(multipartFile).isNotNull();

        bookDto.setCoverImage(multipartFile);

        log.info("File name --> {}", bookDto.getCoverImage().getOriginalFilename());
        cloudStorageServiceImpl.uploadImage(multipartFile, ObjectUtils.asMap(
                "public_id", "library" + extractedFileName(Objects.requireNonNull(bookDto.getCoverImage().getOriginalFilename()))
        ));

        assertThat(bookDto.getCoverImage().getOriginalFilename()).isEqualTo("book_img");
    }

    private String extractedFileName(String filename) {
        return filename.split("\\.")[0];
    }

}