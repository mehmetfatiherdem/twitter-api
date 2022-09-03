package com.example.demo.repository;

import com.example.demo.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {
}
