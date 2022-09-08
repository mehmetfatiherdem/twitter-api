package com.example.demo.services;

import com.example.demo.repository.QuoteTweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class QuoteTweetService {

    @Autowired
    private QuoteTweetRepository quoteTweetRepo;

    public void removeById(UUID id){
        quoteTweetRepo.deleteById(id);
    }
}
