package com.example.demo.services;

import com.example.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TweetService {
    @Autowired
    private TweetRepository tweetRepo;

    public void remove(UUID id){
        tweetRepo.deleteById(id);
    }
}
