package com.example.demo.repository;

import com.example.demo.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {
}
