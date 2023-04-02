package com.application.service.impl;

import com.application.entity.Enum.Role;
import com.application.entity.User;
import com.application.repository.RoleRepository;
import com.application.repository.UserRepository;
import com.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User findUserByName(String name){
        return userRepository.findUserByName(name);
    }
    @Override
    public void saveUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);

    }
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
