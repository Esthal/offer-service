package com.application.service;

import com.application.entity.User;


public interface UserService {
    User findUserByName(String name);
    void saveUser(String name, String password);
}
