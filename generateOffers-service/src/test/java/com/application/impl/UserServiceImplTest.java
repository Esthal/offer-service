package com.application.impl;

import com.application.entity.User;
import com.application.repository.UserRepository;
import com.application.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired private UserRepository userRepository;

    @Test
    public void userSave(){
        User user = new User();
        user.setName("testSave@mail.ru");
        userService.saveUser(user);

        User newUser = userService.getUserByName(user.getName());
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals("testSave@mail.ru", newUser.getName());
        userRepository.delete(user);

    }

    @Test
    public void getAllUsers(){
        List<User> userListOne = userService.getAllUsers();
        Assertions.assertFalse(userListOne.isEmpty());

        User user = new User();
        user.setName("testSave@mail.ru");
        userService.saveUser(user);

        List<User> userListTwo = userService.getAllUsers();
        Assertions.assertFalse(userListTwo.isEmpty());

        Assertions.assertEquals(userListOne.size() + 1, userListTwo.size());

        userRepository.delete(user);
    }
    @Test
    public void getAllUsersByDate(){
        LocalDate getDate = LocalDate.of(2020, 3, 20);
        User user = new User();
        user.setName("testGetByDate@mail.ru");
        user.setDateOfGenerateNewOffer(getDate);
        userService.saveUser(user);

        List<User> users = userService.getAllUserByDateOfGenerateNewOfferBefore(LocalDate.now());
        Assertions.assertNotNull(users);

        users.forEach(item -> Assertions.assertTrue(item.getDateOfGenerateNewOffer().isBefore(LocalDate.now())));
        userRepository.delete(user);
    }

}
