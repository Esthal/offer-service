package com.application.service;

import com.application.entity.OfferHistory;

public interface OfferHistoryService {
    void saveOffer(OfferHistory offerHistory);
    Integer findAverageValueByUserId(long userId);
}
