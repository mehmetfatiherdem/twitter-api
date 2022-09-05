package com.example.demo.repository;

import com.example.demo.model.Bookmark;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookmarkRepository extends CrudRepository<Bookmark, UUID> {
}
