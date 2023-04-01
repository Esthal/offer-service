package com.application.controllers;

import com.application.dto.UserDto;
import com.application.entity.User;
import com.application.service.impl.KafkaProducerServiceImpl;
import com.application.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final KafkaProducerServiceImpl kafkaProducerService;

    @PostMapping("/user/create")
    boolean createUser(@RequestParam(name = "user") String name, @RequestParam(name = "password") String password){
        User existingUser = userService.findUserByName(name);
        if(existingUser != null && existingUser.getName() != null && !existingUser.getName().isEmpty()){
            return false;
        }
        UserDto userDto = new UserDto(name, password);
        userService.saveUser(userDto);
        kafkaProducerService.sendUser(userDto.getName(),userDto.getName());
        return true;
    }

    @GetMapping("/user/{userName}")
    User getUser(@PathVariable String userName) {
        return userService.findUserByName(userName);
    }
}
