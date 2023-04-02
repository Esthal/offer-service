package com.application.impl;

import com.application.entity.User;
import com.application.repository.UserRepository;
import com.application.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceImplTest {
    @Autowired private UserRepository userRepository;
    @Autowired private UserServiceImpl userService;

    @Test
    public void userSave(){
        String name = "test@mail.ru";
        String password = "Test";

        userService.saveUser(name, password);
        User user = userService.findUserByName(name);

        Assertions.assertEquals(user.getName(), name);
        Assertions.assertEquals(user.getPassword(), password);

        userRepository.delete(user);
    }
}
