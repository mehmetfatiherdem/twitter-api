package com.example.demo.repository;

import com.example.demo.model.MessageList;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageListRepository extends CrudRepository<MessageList, UUID> {
}
