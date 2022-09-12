package com.example.demo.repository;

import com.example.demo.model.TweetThread;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ThreadRepository extends CrudRepository<TweetThread, UUID> {
}
