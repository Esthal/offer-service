package com.application.repository;

import com.application.entity.Offer;

import com.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByUserIsOrderById(User user);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM offers WHERE"+
                                            " answer<>'LATER'" +
                                            " AND date_of_answer < :date")
    void deleteAllByDateOfAnswerBefore(@Param("date") LocalDate date);
}
