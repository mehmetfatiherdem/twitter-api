package com.example.demo.services;

import com.example.demo.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepo;
}
