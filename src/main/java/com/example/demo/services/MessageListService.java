package com.example.demo.services;

import com.example.demo.repository.MessageListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MessageListService {
    @Autowired
    private MessageListRepository messageListRepo;

    public void softDelete(UUID id){
        var messageList = messageListRepo.findById(id).orElseThrow();
        messageList.setDeleted(true);
    }

}
