package com.application.service.impl;

import com.application.entity.User;
import com.application.service.KafkaConsumerService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final OfferServiceImpl offerService;
    private final UserServiceImpl userService;

    @Override
    public void listenGroup(String loanJSON) {
        try {
            JSONObject obj = new JSONObject(loanJSON);
            String userName = obj.getString("user_name");
            String msg = obj.getString("message");
            int amount = obj.getInt("amount");

            User user = userService.findUserByName(userName);
            if(user != null){
                offerService.addOffer(msg,
                        user,
                        amount);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
