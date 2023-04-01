package com.application.service.impl;

import com.application.entity.Enum.Answer;
import com.application.entity.Offer;
import com.application.entity.User;
import com.application.repository.OfferRepository;
import com.application.repository.UserRepository;
import com.application.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    @Override
    public void addOffer(String message, User user, int amount){
        Offer offer = new Offer();
        offer.setAnswer(Answer.LATER);
        offer.setMessage(message);
        offer.setUser(user);
        offer.setAmount(amount);
        offerRepository.save(offer);
    }
    @Override
    public void clearOldOffers(LocalDate dateOfDelete){
        offerRepository.deleteAllByDateOfAnswerBefore(dateOfDelete);
    }
    @Override
    public List<Offer> findAllOffersByUserId(long userId){
        List<Offer> offers = new ArrayList<>();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            offers = offerRepository.findAllByUserIsOrderById(userOptional.get());
            offers.removeIf(item -> !item.getAnswer().equals(Answer.LATER));
        }

        return offers;
    }
    @Override
    public Offer updateOffer(long id, Answer answer){
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if(offerOptional.isPresent()){
            Offer offer = offerOptional.get();
            offer.setAnswer(answer);
            offer.setAlreadyAnswered(true);
            offer.setDateOfAnswer(LocalDate.now());
            offerRepository.save(offer);
            return offer;
        }
        return null;
    }

}
