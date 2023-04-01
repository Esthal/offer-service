package com.application.Scheduled;

import com.application.entity.User;
import com.application.service.impl.OfferHistoryServiceImpl;
import com.application.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledTasks {
    private int key;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UserServiceImpl userService;
    private final OfferHistoryServiceImpl offerHistoryService;

    @Value(value = "${spring.kafka.topic-name}")
    private String topicName;


    public void sendMessage(String key, String msg) {
        kafkaTemplate.send(topicName, key, msg);
    }
    @Scheduled(cron = "${spring.kafka.generate-time}")
    public void newOffers() {
        List<User> users = userService.getAllUsers();
        users.stream().map(this::jsonOffer).forEach(item -> sendMessage(String.valueOf(key++),item));
    }

    @Scheduled(cron = "${spring.kafka.auto-generate-time}")
    public void autoGenerateOffers() {
        List<User> users = userService.getAllUserByDateOfGenerateNewOfferBefore(LocalDate.now());
        users.stream().map(this::jsonOffer).forEach(item -> sendMessage(String.valueOf(key++),item));
        users.forEach(item -> {
            item.setDateOfGenerateNewOffer(null);
            userService.saveUser(item);
        });
    }

    private String jsonOffer(User user){
        int amount;
        Integer avg = offerHistoryService.findAverageValueByUserId(user.getId());
        if(avg != null){
            amount = (int) ((Math.random() * (avg*1.5 - avg/2)) + avg/2);
        }else{
            int min = 10;
            int max = 1000;
            amount = (int) ((Math.random() * (max - min)) + min) * 1000;
        }
        Map<String, String> map = new HashMap<>();
        map.put("user_name", user.getName());
        map.put("message", "Apply for a loan");
        map.put("amount", String.valueOf(amount));
        return JSONObject.toJSONString(map);
    }
}
