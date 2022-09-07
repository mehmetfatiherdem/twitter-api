package com.example.demo.repository;

import com.example.demo.model.FollowingList;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FollowingListRepository extends CrudRepository<FollowingList, UUID> {
}
