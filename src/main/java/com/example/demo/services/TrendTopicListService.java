package com.example.demo.services;

import com.example.demo.repository.TrendTopicListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TrendTopicListService {

    @Autowired
    private TrendTopicListRepository trendTopicListRepo;

    public void softDelete(UUID id){
        var trendTopicList = trendTopicListRepo.findById(id).orElseThrow();
        trendTopicList.setDeleted(true);
    }
}
