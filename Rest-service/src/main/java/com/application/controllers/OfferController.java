package com.application.controllers;

import com.application.entity.Enum.Answer;
import com.application.entity.Offer;
import com.application.service.impl.KafkaProducerServiceImpl;
import com.application.service.impl.OfferServiceImpl;
import com.application.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
public class OfferController {
    private final OfferServiceImpl offerService;
    private final UserServiceImpl userService;
    private final KafkaProducerServiceImpl kafkaProducerService;
    @GetMapping("/offers/{userName}")
    List<Offer> getUserOffers(@PathVariable String userName) {
        return offerService.findAllOffersByUserId(
                userService.findUserByName(userName).getId()
        );
    }

    @PostMapping("/update/{id}")
    void updateOffer(@PathVariable long id, @RequestParam(name = "answer") Answer answer){
        Offer updateOffer = offerService.updateOffer(id, answer);
        if(updateOffer != null){
            if(!answer.equals(Answer.LATER)){
                kafkaProducerService.sendOffer(String.valueOf(updateOffer.getId()), updateOffer);
            }
        }
    }
}
