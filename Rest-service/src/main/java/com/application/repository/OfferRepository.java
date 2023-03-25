package com.application.repository;

import com.application.entity.Offer;

import com.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByUserIsOrderById(User user);
    List<Offer> findAllByAlreadyAnsweredTrue();
}
