package com.EmailService.services;

import org.springframework.stereotype.Service;

import com.EmailService.entities.Users;
import com.EmailService.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void saveUser(String name, String email) {

        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
    }
}
