package com.application.service;

import com.application.dto.UserDto;
import com.application.entity.User;


public interface UserService {
    User findUserByName(String name);
    void saveUser(UserDto userDto);
}
