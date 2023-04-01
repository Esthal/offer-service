package com.application.impl;

import com.application.dto.UserDto;
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
        UserDto userDto = new UserDto();
        userDto.setName("test@mail.ru");
        userDto.setPassword("Test");

        userService.saveUser(userDto);
        User user = userService.findUserByName(userDto.getName());

        Assertions.assertEquals(user.getName(), userDto.getName());
        Assertions.assertEquals(user.getPassword(), userDto.getPassword());

        userRepository.delete(user);
    }
}
