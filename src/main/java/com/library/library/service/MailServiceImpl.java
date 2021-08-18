package com.library.library.service;

import com.library.library.domain.models.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

    @Override
    public void sendMailWithAttachments(Mail mail) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(mail.getMailTo());
        helper.setSubject(mail.getMailSubject());
        helper.setText(msg.getContentType());
        helper.addAttachment(mail.getContentType(), new ClassPathResource(mail.getAttachments().toString()));

        javaMailSender.send(msg);
    }
}
