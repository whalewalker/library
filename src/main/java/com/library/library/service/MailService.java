package com.library.library.service;

import com.library.library.domain.models.Mail;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(Mail mail);
}
