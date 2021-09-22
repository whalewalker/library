package com.library.library.service;

import com.cloudinary.utils.ObjectUtils;
import com.library.library.domain.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
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

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class CloudinaryTest {

    @Autowired
    CloudStorageService cloudStorageService;

    @Test
    void uploadImageMultipartFile() {
        File file = new File("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/me.jpeg");
        assertThat(file.exists()).isTrue();
        Map<Object, Object> params = new HashMap<>();

        params.put("public_id", "library/user_file");
        params.put("overwrite", true);

        try {
            cloudStorageService.uploadImage(file, params);
        } catch (IOException e) {
            log.info("Error occurred --> {}", e.getMessage());
        }
    }

    @Test
    @DisplayName("Upload multiple image test")
    void uploadMultiPartImageTest() throws IOException {

        Path path = Paths.get("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/me.jpeg");
        MultipartFile multipartFile = new MockMultipartFile("author-image.jpeg", "author-image.jpeg", "img/jpeg", Files.readAllBytes(path));

        log.info("Multipart Object created --> {}", multipartFile);
        assertThat(multipartFile).isNotNull();

        cloudStorageService.uploadImage(multipartFile, ObjectUtils.asMap(
                "public_id", "libraryApp" + cloudStorageService.extractFileName(Objects.requireNonNull(multipartFile.getOriginalFilename()))
        ));
        assertThat(multipartFile.getOriginalFilename()).isEqualTo("author-image.jpeg");
    }


    @Test
    void extractImageFromPdf() throws IOException {
        File pdfFile = new File("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/Spring Boot Up and Running Building Cloud Native Java and Kotlin Applications by Mark Heckler [Mark Heckler] (z-lib.org).pdf");
        assertThat(pdfFile.exists()).isTrue();
        assertThat(pdfFile.getName()).isEqualTo("Spring Boot Up and Running Building Cloud Native Java and Kotlin Applications by Mark Heckler [Mark Heckler] (z-lib.org).pdf");

        cloudStorageService.extractCoverImage(pdfFile);
        File extractedImage = new File("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/Pdf.png");
        assertThat(extractedImage.exists()).isTrue();
        assertThat(extractedImage.getName()).isEqualTo("Pdf.png");
    }

    @Test
    void uploadPdfToCloudinary(){
        File pdfFile = new File("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/Spring Boot Up and Running Building Cloud Native Java and Kotlin Applications by Mark Heckler [Mark Heckler] (z-lib.org).pdf");
        assertThat(pdfFile.exists()).isTrue();
        assertThat(pdfFile.getName()).isEqualTo("Spring Boot Up and Running Building Cloud Native Java and Kotlin Applications by Mark Heckler [Mark Heckler] (z-lib.org).pdf");

        Map<Object, Object> params = new HashMap<>();
        params.put("public_id", "books/pdf_file");
        params.put("overwrite", true);
        params.put("pages", true);
        params.put("resource_type", "raw");


        try {
            Map<?, ?> pdfMap = cloudStorageService.uploadPdf(pdfFile, params);
            log.info("Pdf return data ==> {}", pdfMap);
        } catch (IOException e) {
            log.info("Error occurred --> {}", e.getMessage());
        }
    }
}
