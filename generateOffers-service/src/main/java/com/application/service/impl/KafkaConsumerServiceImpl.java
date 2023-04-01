package com.application.service.impl;

import com.application.entity.Enum.Answer;
import com.application.entity.OfferHistory;
import com.application.entity.User;
import com.application.service.KafkaConsumerService;
import com.application.service.impl.OfferHistoryServiceImpl;
import com.application.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final UserServiceImpl userService;
    private final OfferHistoryServiceImpl offerHistoryService;

    @Override
    public void listenUser(String email) {
        User user = new User();
        user.setName(email);
        userService.saveUser(user);
    }

    @Override
    public void listenOffer(String offer) {
        try {
            JSONObject obj = new JSONObject(offer);
            String name = obj.getJSONObject("user").getString("name");
            String answer = obj.getString("answer");
            int amount = obj.getInt("amount");
            JSONArray date = obj.getJSONArray("dateOfAnswer");
            LocalDate dateOfAnswer = LocalDate.of((int)date.get(0), (int)date.get(1), (int)date.get(2));


            User user = userService.getUserByName(name);

            OfferHistory offerHistory = new OfferHistory();
            offerHistory.setUser(user);
            offerHistory.setAmount(amount);
            offerHistory.setAnswer(Answer.valueOf(answer));
            offerHistory.setDateOfAnswer(dateOfAnswer);
            if(answer.equals("ACCEPTED")){
                user.setDateOfGenerateNewOffer(dateOfAnswer.plusMonths(3));
            }else{
                user.setDateOfGenerateNewOffer(dateOfAnswer.plusMonths(1));
            }

            userService.saveUser(user);
            offerHistoryService.saveOffer(offerHistory);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
