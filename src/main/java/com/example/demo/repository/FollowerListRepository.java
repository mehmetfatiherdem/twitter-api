package com.example.demo.repository;

import com.example.demo.model.FollowerList;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FollowerListRepository extends CrudRepository<FollowerList, UUID> {
}
