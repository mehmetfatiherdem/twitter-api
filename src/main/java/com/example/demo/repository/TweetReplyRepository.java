package com.example.demo.repository;

import com.example.demo.model.TweetReply;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TweetReplyRepository extends CrudRepository<TweetReply, UUID> {
}
