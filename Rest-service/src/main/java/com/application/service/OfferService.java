package com.application.service;

import com.application.entity.Enum.Answer;
import com.application.entity.Offer;
import com.application.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface OfferService {
    void addOffer(String message, User user, int amount);
    void clearOldOffers(LocalDate dateOfDelete);
    List<Offer> findAllOffersByUserId(long userId);
    Offer updateOffer(long id, Answer answer);
}
