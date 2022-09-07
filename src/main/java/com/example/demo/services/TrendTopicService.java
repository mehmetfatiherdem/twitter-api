package com.example.demo.services;

import com.example.demo.repository.TrendTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TrendTopicService {
    @Autowired
    private TrendTopicRepository trendTopicRepo;

    public void removeById(UUID id){trendTopicRepo.deleteById(id);}
}
