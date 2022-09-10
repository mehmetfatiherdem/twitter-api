package com.example.demo.services;

import com.example.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class TweetService {
    @Autowired
    private TweetRepository tweetRepo;

    public void softDelete(UUID id){
        var tweet = tweetRepo.findById(id).orElseThrow();
        tweet.setDeleted(true);
    }

}
