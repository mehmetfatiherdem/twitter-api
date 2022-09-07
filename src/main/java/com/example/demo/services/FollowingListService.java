package com.example.demo.services;

import com.example.demo.repository.FollowingListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class FollowingListService {
    @Autowired
    private FollowingListRepository followingListRepo;

    public void removeById(UUID id){
        followingListRepo.deleteById(id);
    }
}
