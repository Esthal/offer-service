package com.application.controllers;

import com.application.entity.Enum.Answer;
import com.application.entity.Offer;
import com.application.repository.OfferRepository;
import com.application.repository.UserRepository;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OfferController {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    public OfferController(OfferRepository offerRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/offers/{id}")
    List<Offer> all(@PathVariable long id) {

         List<Offer> offers = offerRepository.findAllByUserIsOrderById(userRepository.findById(id).get());
        return offers.stream().filter( item -> item.getAnswer() == Answer.LATER || !item.isAlreadyAnswered()).collect(Collectors.toList());
    }

    @PostMapping("/update/{id}")
    void updateOffer(@PathVariable long id, @RequestParam(name = "answer") Answer answer){
        if(offerRepository.findById(id).isPresent()){
            Offer offer = offerRepository.findById(id).get();
            offer.setAnswer(answer);
            offer.setAlreadyAnswered(true);
            offer.setDateOfAnswer(LocalDate.now());
            offerRepository.save(offer);
        }
    }
}
