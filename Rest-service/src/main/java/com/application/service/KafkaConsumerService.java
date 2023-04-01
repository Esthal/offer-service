package com.application.service;

import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaConsumerService {
    @KafkaListener(topics = "${spring.kafka.topic-name}", groupId = "group")
    void listenGroup(String loanJSON);
}
