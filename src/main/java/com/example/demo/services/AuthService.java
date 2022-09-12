package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {
    @Autowired
    private UserRepository userRepo;

    private boolean userExists(String email){
        User user = userRepo.findByEmail(email);
        if(user != null) return true;
        return false;
    }
}
