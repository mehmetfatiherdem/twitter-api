package com.example.demo.services;

import com.example.demo.dto.TweetCreateDTO;
import com.example.demo.model.Tweet;

public interface ITweetService {
    Tweet create(TweetCreateDTO dto);
}
