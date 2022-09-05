package com.example.demo.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@SQLDelete(sql = "UPDATE tweet SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Tweet {
    @Id
    @GeneratedValue
    private UUID id;

    //FIXME: add join column here and mapped by user in user model
    @ManyToOne
    @Column(name = "user")
    private User user;

    @Column(name = "text", length = 140)
    private String text;

    @Column(name = "like_count")
    private Long likeCount;

    @Column(name = "retweet_count")
    private Long retweetCount;

    @Column(name = "quote_tweet_count")
    private Long quoteTweetCount;

    @ManyToMany(mappedBy = "bookmarkedTweets")
    Set<Bookmark> bookmarksBelongTo;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    @PreUpdate
    protected void updateTimeStamp(){
        updatedAt = new Date();
        if(createdAt == null) createdAt = new Date();
    }

    public Tweet(){}

    public UUID getId() {
        return id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Long retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Long getQuoteTweetCount() {
        return quoteTweetCount;
    }

    public void setQuoteTweetCount(Long quoteTweetCount) {
        this.quoteTweetCount = quoteTweetCount;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }
}
