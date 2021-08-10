package com.library.library;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.library.library.web.config.CloudinaryConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import javax.sql.DataSource;

@SpringBootApplication
@EnableMongoAuditing
public class LibraryApplication {

    @Autowired
    CloudinaryConfig cloudinaryConfig;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryConfig.getCloudName(),
                "api_key", cloudinaryConfig.getApiKey(),
                "api_secret", cloudinaryConfig.getApiSecret()
        ));
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
