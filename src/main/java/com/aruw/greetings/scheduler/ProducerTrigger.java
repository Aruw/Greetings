package com.aruw.greetings.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.aruw.greetings.model.Message;
import com.aruw.greetings.util.HtmlLoader;
import org.springframework.stereotype.Component;
import com.aruw.greetings.service.GreetingsProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerTrigger {

    @Value("${email.address.destiny}")
    private String destinyAddress;

    private final GreetingsProducer producer;

    @Scheduled(cron = "*/20 * * * * *")
    public void htmlMailScheduler() {
        log.info("Scheduler - Mail triggering producer!");
        Message newMessage = Message.builder()
                .address(destinyAddress)
                .isHtmlContent(Boolean.TRUE)
                .subject("Is it really Kafka?")
                .content(HtmlLoader.loadHtmlFile())
                .build();
        producer.sendMessage(newMessage);
    }

    @Scheduled(cron = "*/40 * * * * *")
    public void simpleMailScheduler() {
        log.info("Scheduler - Simple triggering producer!");
        Message newMessage = Message.builder()
                .address(destinyAddress)
                .isHtmlContent(Boolean.TRUE)
                .subject("Kafka says hello!")
                .content("A squirrel has been promoted to captain of a spaceship \uD83D\uDC3F\uFE0F\uD83D\uDE80 and you are the co-pilot \uD83C\uDF0C✨\n")
                .build();
        producer.sendMessage(newMessage);
    }

}