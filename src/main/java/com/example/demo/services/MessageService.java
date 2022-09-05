package com.example.demo.services;

import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MessageService {
    @Autowired
    private MessageRepository messageRepo;

    public void removeById(UUID id){
        messageRepo.deleteById(id);
    }
}
