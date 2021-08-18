package com.library.library.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class Mail {
    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List< Object > attachments;

    public Mail() {
        contentType = "text/plain";
    }

    public Mail(String recipient, String subject, String message) {
        this.mailTo = recipient;
        this.mailSubject = subject;
        this.mailContent = message;
    }
}
