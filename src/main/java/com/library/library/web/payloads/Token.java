package com.library.library.web.payloads;

import com.library.library.domain.models.Author;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Token {
    @Id
    private String id;
    private String token;

    @DBRef
    private Author user;
    private LocalDateTime expiryTimeStamp;
}
