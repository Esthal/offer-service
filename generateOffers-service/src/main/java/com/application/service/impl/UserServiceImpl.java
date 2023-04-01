package com.application.service.impl;

import com.application.entity.User;
import com.application.repository.UserRepository;
import com.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }
    @Override
    public User getUserByName(String name){
        return userRepository.findUserByName(name);
    }
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public List<User> getAllUserByDateOfGenerateNewOfferBefore(LocalDate localDate){
        return userRepository.findAllByDateOfGenerateNewOfferBefore(localDate);
    }


}
