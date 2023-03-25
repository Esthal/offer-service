package com.application.service;

import com.application.entity.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

    private final OfferService offerService;
    private final UserService userService;

    public KafkaConsumer(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-name}", groupId = "group")
    public void listenGroup(String message) {
        List<User> list = userService.getAllUsers();
        list.forEach(item ->  offerService.addOffer(message, item));
    }
}
