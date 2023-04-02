package com.application.impl;

import com.application.entity.Enum.Answer;
import com.application.entity.Offer;
import com.application.entity.User;
import com.application.repository.UserRepository;
import com.application.service.impl.OfferServiceImpl;
import com.application.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class OfferServiceImplTest {
    @Autowired private UserRepository userRepository;
    @Autowired private UserServiceImpl userService;
    @Autowired private OfferServiceImpl offerService;

    @Test
    public void userAddOffer(){
        String name = "test@mail.ru";
        String password = "Test";

        userService.saveUser(name, password);
        User user = userService.findUserByName(name);

        String msg = "Message";
        int amount = 5000;

        offerService.addOffer(msg, user, amount);
        List<Offer> offers = offerService.findAllOffersByUserId(user.getId());

        Assertions.assertNotNull(offers);
        Assertions.assertEquals(1,offers.size());
        offers.forEach(item ->{
            Assertions.assertEquals(user.getId(),item.getUser().getId());
        });
        offers.forEach(item ->
            offerService.updateOffer(item.getId(), Answer.ACCEPTED)
        );
        offerService.clearOldOffers(LocalDate.now().plusYears(1));
        userRepository.delete(user);
    }


    @Test
    public void userUpdateOffer(){
        String name = "test@mail.ru";
        String password = "Test";

        userService.saveUser(name, password);
        User user = userService.findUserByName(name);

        String msg = "Message";
        int amount = 5000;

        offerService.addOffer(msg, user, amount);
        List<Offer> offers = offerService.findAllOffersByUserId(user.getId());

        offers.forEach(item ->
                Assertions.assertEquals(Answer.LATER, item.getAnswer())
        );

        offers.forEach(item ->{
            offerService.updateOffer(item.getId(), Answer.ACCEPTED);
            System.out.println(item.getId());
        });
        offers = offerService.findAllOffersByUserId(user.getId());

        offers.forEach(item ->{
            Assertions.assertEquals(Answer.ACCEPTED, item.getAnswer());
            Assertions.assertNotNull(item.getDateOfAnswer());
            Assertions.assertTrue(item.isAlreadyAnswered());
        });
        offerService.clearOldOffers(LocalDate.now().plusYears(1));
        userRepository.delete(user);
    }


    @Test
    public void userClearOldOffers(){
        String name = "test@mail.ru";
        String password = "Test";

        userService.saveUser(name, password);
        User user = userService.findUserByName(name);

        String msg = "Message";
        int amount = 5000;

        offerService.addOffer(msg, user, amount);
        List<Offer> offers = offerService.findAllOffersByUserId(user.getId());

        Assertions.assertEquals(1,offers.size());

        offers.forEach(item ->
                offerService.updateOffer(item.getId(), Answer.ACCEPTED)
        );
        offerService.clearOldOffers(LocalDate.now().plusYears(1));

        offers = offerService.findAllOffersByUserId(user.getId());
        Assertions.assertEquals(0,offers.size());

        userRepository.delete(user);
    }
}
