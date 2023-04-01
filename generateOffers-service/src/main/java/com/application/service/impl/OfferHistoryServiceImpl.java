package com.application.service.impl;

import com.application.entity.OfferHistory;
import com.application.repository.OfferHistoryRepository;
import com.application.service.OfferHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfferHistoryServiceImpl implements OfferHistoryService {
    private final OfferHistoryRepository offerHistoryRepository;
    @Override
    public void saveOffer(OfferHistory offerHistory){
        offerHistoryRepository.save(offerHistory);
    }
    @Override
    public Integer findAverageValueByUserId(long userId){
        return offerHistoryRepository.findAvgByUserId(userId);
    }
}
