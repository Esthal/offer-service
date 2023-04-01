package com.application.service;

import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaConsumerService {
    @KafkaListener(topics = "${spring.kafka.topic-producer-generate-user}", groupId = "group")
    void listenUser(String email);
    @KafkaListener(topics = "${spring.kafka.topic-producer-generate-answer}", groupId = "group")
    void listenOffer(String offer);
}
