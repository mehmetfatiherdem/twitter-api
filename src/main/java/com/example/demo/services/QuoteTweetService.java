package com.example.demo.services;

import com.example.demo.repository.QuoteTweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class QuoteTweetService {

    @Autowired
    private QuoteTweetRepository quoteTweetRepo;

    public void softDelete(UUID id){
        var quoteTweet = quoteTweetRepo.findById(id).orElseThrow();
        quoteTweet.setDeleted(true);
    }

}
