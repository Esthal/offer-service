package com.application.impl;

import com.application.entity.Enum.Answer;
import com.application.entity.OfferHistory;
import com.application.entity.User;
import com.application.repository.OfferHistoryRepository;
import com.application.repository.UserRepository;
import com.application.service.impl.OfferHistoryServiceImpl;
import com.application.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class OfferHistoryServiceImplTest {
    @Autowired
    private OfferHistoryServiceImpl offerHistoryService;
    @Autowired
    private OfferHistoryRepository offerHistoryRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired private UserRepository userRepository;

    @Test
    public void findAvg(){
        User user = new User();
        user.setName("testAvg@mail.ru");
        userService.saveUser(user);
        user = userService.getUserByName(user.getName());


        OfferHistory offerOne = new OfferHistory();
        offerOne.setUser(user);
        offerOne.setAmount(5000);
        offerOne.setAnswer(Answer.ACCEPTED);
        offerOne.setDateOfAnswer(LocalDate.now());

        OfferHistory offerTwo = new OfferHistory();
        offerTwo.setUser(user);
        offerTwo.setAmount(7000);
        offerTwo.setAnswer(Answer.ACCEPTED);
        offerTwo.setDateOfAnswer(LocalDate.now());

        offerHistoryService.saveOffer(offerOne);
        offerHistoryService.saveOffer(offerTwo);

        Integer avg = offerHistoryService.findAverageValueByUserId(user.getId());
        Assertions.assertEquals(6000, avg);


        offerHistoryRepository.delete(offerOne);
        offerHistoryRepository.delete(offerTwo);
        userRepository.delete(user);


    }

}
