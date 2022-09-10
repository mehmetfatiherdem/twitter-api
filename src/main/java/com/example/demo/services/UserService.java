package com.example.demo.services;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void softDelete(UUID id){
        var user = userRepo.findById(id).orElseThrow();
        user.setDeleted(true);
    }

}
