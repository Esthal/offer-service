package com.application.service.impl;

import com.application.entity.Offer;
import com.application.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Offer> kafkaOffer;

    @Value(value = "${spring.kafka.topic-producer-generate-user}")
    private String topicName;
    @Value(value = "${spring.kafka.topic-producer-generate-answer}")
    private String topicAnswer;
    @Override
    public void sendUser(String key, String msg) {
        kafkaTemplate.send(topicName, key, msg);
    }
    @Override
    public void sendOffer(String key, Offer offer) {
        kafkaOffer.send(topicAnswer, key, offer);
    }


}
