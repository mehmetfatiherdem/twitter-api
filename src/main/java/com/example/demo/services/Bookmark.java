package com.example.demo.services;

import com.example.demo.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Bookmark {
    @Autowired
    private BookmarkRepository bookmarkRepo;
}
