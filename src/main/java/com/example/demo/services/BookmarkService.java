package com.example.demo.services;

import com.example.demo.model.Bookmark;
import com.example.demo.repository.BookmarkRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.UUID;

public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepo;

    @Autowired
    private EntityManager entityManager;

    public void softDelete(UUID id){
        var bookmark = bookmarkRepo.findById(id).orElseThrow();
        bookmark.setDeleted(true);
    }

    public Iterable<Bookmark> findAll(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Bookmark> bookmarks = bookmarkRepo.findAll();
        session.disableFilter("deletedFilter");
        return bookmarks;
    }
}
