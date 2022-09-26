package com.example.demo.services;

import com.example.demo.exception.auth.UserDoesNotExistException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepo;

    public void softDelete(UUID id){
        var user = userRepo.findById(id).orElseThrow();
        user.setDeleted(true);
    }

    public User getUserById(UUID id){
        var user = userRepo.findById(id).orElseThrow(() -> new UserDoesNotExistException(id.toString()));

        return user;
    }

}
