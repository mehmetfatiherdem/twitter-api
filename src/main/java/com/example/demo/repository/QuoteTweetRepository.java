package com.example.demo.repository;

import com.example.demo.model.QuoteTweet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuoteTweetRepository extends CrudRepository<QuoteTweet, UUID> {
}
