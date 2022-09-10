package com.example.demo.services;

import com.example.demo.repository.TweetReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class TweetReplyService {

    @Autowired
    private TweetReplyRepository tweetReplyRepo;

    public void softDelete(UUID id){
        var tweetReply = tweetReplyRepo.findById(id).orElseThrow();
        tweetReply.setDeleted(true);
    }

}
