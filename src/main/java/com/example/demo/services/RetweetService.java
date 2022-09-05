package com.example.demo.services;

import com.example.demo.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class RetweetService {
    @Autowired
    private RetweetRepository retweetRepo;

    public void removeById(UUID id){
        retweetRepo.deleteById(id);
    }
}
