package com.library.library.service;

import com.cloudinary.Cloudinary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudStorageServiceImpl implements CloudStorageService {
//    @Autowired
//    Cloudinary cloudinary;
//
//    @Override
//    public void uploadImage(File file, Map<?, ?> imageProperties) throws IOException {
//        cloudinary.uploader().upload(file, imageProperties);
//    }
//
//    @Override
//    public Map<?, ?> uploadImage(MultipartFile file, Map<?, ?> imageProperties) throws IOException {
//        return cloudinary.uploader().upload(file.getBytes(), imageProperties);
//    }
//
//    @Override
//    public void extractCoverImage(File pdfFile) throws IOException {
//        PDDocument pdfDocument = PDDocument.load(pdfFile);
//        PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
//
//        BufferedImage image = pdfRenderer.renderImage(0);
//        ImageIO.write(image, "JPEG", new File("/home/whalewalker/Whalewalker/Personal/library/src/main/resources/static/Pdf.png"));
//
//        pdfDocument.close();
//    }
//
//    public String extractFileName(String fileName) {
//        return fileName.split("\\.")[0];
//    }
//
//    @Override
//    public Map<?, ?> uploadPdf(File file, Map<?, ?> pdfProperties) throws IOException {
//        return cloudinary.uploader().upload(file, pdfProperties);
//    }
}
