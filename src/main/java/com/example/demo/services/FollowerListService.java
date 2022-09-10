package com.example.demo.services;

import com.example.demo.repository.FollowerListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class FollowerListService {
    @Autowired
    private FollowerListRepository followerListRepo;

    public void removeById(UUID id){
        followerListRepo.deleteById(id);
    }
}
