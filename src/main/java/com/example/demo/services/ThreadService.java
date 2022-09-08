package com.example.demo.services;

import com.example.demo.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ThreadService {
    @Autowired
    private ThreadRepository threadRepo;
}
