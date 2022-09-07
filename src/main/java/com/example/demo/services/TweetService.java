package com.example.demo.services;

import com.example.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TweetService {
    @Autowired
    private TweetRepository tweetRepo;

    //TODO: check all services and models if the soft-delete is necessary and/or implemented correctly

    public void removeById(UUID id){
        tweetRepo.deleteById(id);
    }
}
