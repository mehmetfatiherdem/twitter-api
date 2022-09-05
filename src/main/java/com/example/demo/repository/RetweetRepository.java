package com.example.demo.repository;

import com.example.demo.model.Retweet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RetweetRepository extends CrudRepository<Retweet, UUID> {
}
