package com.application.Scheduled;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;



@Configuration
@EnableScheduling
public class ScheduledTasks {
    private int key;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${spring.kafka.topic-name}")
    private String topicName;

    public ScheduledTasks(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String key, String msg) {
        kafkaTemplate.send(topicName, key, msg);
    }
    @Scheduled(cron = "${spring.kafka.generate-time}")
    public void computePrice() {
        sendMessage(String.valueOf(key++),"Apply for a loan");

    }

}
