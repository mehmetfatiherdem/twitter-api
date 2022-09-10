package com.example.demo.services;

import com.example.demo.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ThreadService {
    @Autowired
    private ThreadRepository threadRepo;

    public void softDelete(UUID id){
        var thread = threadRepo.findById(id).orElseThrow();
        thread.setDeleted(true);
    }
}
