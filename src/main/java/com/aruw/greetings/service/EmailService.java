package com.aruw.greetings.service;

import jakarta.mail.MessagingException;
import com.aruw.greetings.model.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String originAddress;

    private final JavaMailSender mailSender;

    @Async("mailExecutor")
    public void sendSimpleMessage(Message message) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(originAddress);
        smm.setTo(message.getAddress());
        smm.setSubject(message.getSubject());
        smm.setText(message.getContent());
        mailSender.send(smm);
    }

    @Async("mailExecutor")
    public void sendHtmlMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(originAddress);
        helper.setTo(message.getAddress());
        helper.setSubject(message.getSubject());
        helper.setText(message.getContent(), true);
        mailSender.send(mimeMessage);
    }

}