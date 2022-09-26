package com.example.demo.services;

import com.example.demo.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    User getUserById(UUID id);
    List<User> getAllUsers();
}
