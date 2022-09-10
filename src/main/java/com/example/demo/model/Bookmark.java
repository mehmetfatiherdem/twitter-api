package com.example.demo.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Bookmark")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Bookmark {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "bookmark")
    private User owner;

    @ManyToMany
    @JoinTable(name = "bookmarked_tweet", joinColumns = @JoinColumn(name = "bookmark_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
    Set<Tweet> bookmarkedTweets;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;



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

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
        this.deletedAt = new Date();
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

}
