package com.application.service;

import com.application.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void saveUser(User user);
    User getUserByName(String name);
    List<User> getAllUsers();
    List<User> getAllUserByDateOfGenerateNewOfferBefore(LocalDate localDate);
}
