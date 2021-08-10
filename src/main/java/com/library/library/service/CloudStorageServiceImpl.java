package com.library.library.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudStorageServiceImpl implements CloudStorageService{
    @Override
    public void uploadImage(File file, Map<?, ?> imageProperties) throws IOException {

    }

    @Override
    public Map<?, ?> uploadImage(MultipartFile file, Map<?, ?> imageProperties) throws IOException {
        return null;
    }
}
