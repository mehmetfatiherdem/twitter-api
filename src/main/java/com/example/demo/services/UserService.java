package com.example.demo.services;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void removeById(UUID id){
        userRepo.deleteById(id);
    }
}
