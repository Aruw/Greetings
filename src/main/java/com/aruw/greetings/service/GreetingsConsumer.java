package com.aruw.greetings.service;

import java.util.Set;
import jakarta.validation.Validator;
import jakarta.mail.MessagingException;
import com.aruw.greetings.model.Message;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class GreetingsConsumer {

    private final Validator validator;

    private final EmailService emailService;

    @KafkaListener(topics = "greetings", groupId = "greetings-group")
    public void greetingsListener(Message message) throws MessagingException {
        Set<ConstraintViolation<Message>> violations = validator.validate(message);

        if (violations.isEmpty()) {
            if (message.getIsHtmlContent())
                emailService.sendHtmlMessage(message);
            else
                emailService.sendSimpleMessage(message);
        } else {
            log.error("Invalid message: " + violations);
        }
    }

}