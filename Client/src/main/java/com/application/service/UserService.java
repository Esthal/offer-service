package com.application.service;


import com.application.dto.UserDto;
import com.application.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByName(String name);
}
