package com.example.demo.services;

import com.example.demo.repository.MessageListRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageListService {
    @Autowired
    private MessageListRepository messageListRepo;

}
