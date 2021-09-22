package com.library.library.service;

import com.library.library.domain.models.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendMail(Mail mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getMailTo());

        msg.setSubject(mail.getMailSubject());
        msg.setText(mail.getMailContent());

        javaMailSender.send(msg);
    }
}
