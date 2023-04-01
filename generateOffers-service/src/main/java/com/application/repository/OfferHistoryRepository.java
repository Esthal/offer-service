package com.application.repository;

import com.application.entity.OfferHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OfferHistoryRepository extends JpaRepository<OfferHistory, Long> {
    @Query(nativeQuery = true, value = "SELECT  AVG(amount) as price"+
            " from history_of_offers" +
            " WHERE user_id = :userId")
    Integer findAvgByUserId(long userId);
}
