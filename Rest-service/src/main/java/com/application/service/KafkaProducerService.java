package com.application.service;

import com.application.entity.Offer;

public interface KafkaProducerService {
    void sendUser(String key, String msg);

    void sendOffer(String key, Offer offer);
}
