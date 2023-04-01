package com.application.scheduled;



import com.application.service.impl.OfferServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class ScheduledTasks {

    private final OfferServiceImpl offerService;
    @Scheduled(cron = "${spring.kafka.delete-offers-time}")
    public void deleteOffers() {
        offerService.clearOldOffers(LocalDate.now().minusMonths(6));
    }

}
