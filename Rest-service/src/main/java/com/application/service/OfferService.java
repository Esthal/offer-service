package com.application.service;

import com.application.entity.Enum.Answer;
import com.application.entity.Offer;
import com.application.entity.User;
import com.application.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferService {
    private OfferRepository offerRepository;
    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
    public void addOffer(String message, User user){
        Offer offer = new Offer();
        offer.setAnswer(Answer.LATER);
        offer.setMessage(message);
        offer.setUser(user);
        offerRepository.save(offer);
    }

}
