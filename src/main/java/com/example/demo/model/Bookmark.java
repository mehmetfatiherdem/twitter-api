package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "bookmark")
    private User owner;

    @ManyToMany
    @JoinTable(name = "bookmarked_tweet", joinColumns = @JoinColumn(name = "bookmark_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    Set<Tweet> bookmarkedTweets;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;


    @PreUpdate
    @PrePersist
    protected void updateTimeStamps(){
        updatedAt = new Date();
        if(createdAt == null) createdAt = new Date();
    }

    public Bookmark(){}

    public UUID getId(){
        return this.id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Set<Tweet> getBookmarkedTweets() {
        return bookmarkedTweets;
    }

    public void setBookmarkedTweets(Set<Tweet> bookmarkedTweets) {
        this.bookmarkedTweets = bookmarkedTweets;
    }
}
