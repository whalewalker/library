package com.library.library.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CloudStorageService {
    void uploadImage(File file, Map<?, ?> imageProperties) throws IOException;
    Map<?, ?> uploadImage(MultipartFile file, Map<?, ?> imageProperties) throws IOException;
    void extractCoverImage(File pdfFile) throws IOException;
    String extractFileName(String fileName);
    Map<?, ?> uploadPdf(File file, Map<?, ?> pdfProperties) throws IOException;
}
