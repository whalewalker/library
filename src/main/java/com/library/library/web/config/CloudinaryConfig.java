package com.library.library.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CloudinaryConfig {
    @Value("${app.CLOUD_NAME}")
    private String cloudName;
    @Value("${app.API_KEY}")
    private String apiKey;
    @Value("${app.API_SECRET}")
    private String apiSecret;
}
