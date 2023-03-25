package com.application.scheduled;


import com.application.entity.Enum.Answer;
import com.application.entity.Offer;
import com.application.repository.OfferRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledTasks {
    private final OfferRepository offerRepository;
    public ScheduledTasks(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
    @Scheduled(cron = "${spring.kafka.delete-offers-time}")
    public void deleteOffers() {
        LocalDate dateOfDelete = LocalDate.now().minusMonths(6);
        List<Offer> list = offerRepository.findAll();
        list.removeIf(item -> item.getAnswer().equals(Answer.LATER));
        list.stream()
                .filter( item -> item.getDateOfAnswer().isBefore(dateOfDelete))
                .forEach( item -> offerRepository.deleteById(item.getId()));
    }
    @Scheduled(cron = "${spring.kafka.create-offer-time}")
    public void updateOffers() {
        LocalDate dateOfGenerateAccepted = LocalDate.now().minusMonths(3);
        LocalDate dateOfGenerateRefused = LocalDate.now().minusMonths(1);

        List<Offer> list = offerRepository.findAllByAlreadyAnsweredTrue();
        list.removeIf(item -> item.getAnswer().equals(Answer.LATER));
        list.forEach(item -> {
            if(
                    (item.getAnswer().equals(Answer.ACCEPTED)
                            && item.getDateOfAnswer().isBefore(dateOfGenerateAccepted))
                    ||
                    (item.getAnswer().equals(Answer.REFUSED)
                            && item.getDateOfAnswer().isBefore(dateOfGenerateRefused))
            ){
                Offer offer = new Offer();
                offer.setMessage(item.getMessage());
                offer.setAnswer(item.getAnswer());
                offer.setDateOfAnswer(item.getDateOfAnswer());
                offer.setAlreadyAnswered(false);
                offer.setUser(item.getUser());
                offerRepository.save(offer);
            }
        });



    }
}
