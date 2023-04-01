package com.application.repository;

import com.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
    List<User> findAllByDateOfGenerateNewOfferBefore(LocalDate localDate);
}
