package com.library.library.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

}