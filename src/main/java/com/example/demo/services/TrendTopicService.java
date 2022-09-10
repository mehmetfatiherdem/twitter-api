package com.example.demo.services;

import com.example.demo.repository.TrendTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


public class TrendTopicService {
    @Autowired
    private TrendTopicRepository trendTopicRepo;

    public void softDelete(UUID id){
        var trendTopic = trendTopicRepo.findById(id).orElseThrow();
        trendTopic.setDeleted(true);
    }

}
