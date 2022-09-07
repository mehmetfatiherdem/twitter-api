package com.example.demo.repository;

import com.example.demo.model.TrendTopic;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TrendTopicRepository extends CrudRepository<TrendTopic, UUID> {
}
