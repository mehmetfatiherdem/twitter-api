package com.example.demo.repository;

import com.example.demo.model.Tweet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TweetRepository extends CrudRepository<Tweet, UUID> {
}
