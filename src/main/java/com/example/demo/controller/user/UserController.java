package com.example.demo.controller.user;

import com.example.demo.model.User;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable String id){
        UUID uuid = UUID.fromString(id);
        return userService.getUserById(uuid);
    }
}
