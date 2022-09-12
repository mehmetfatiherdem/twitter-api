package com.example.demo.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Tweet {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
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

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private Set<Retweet> retweets;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private Set<TweetReply> tweetReplies;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private Set<QuoteTweet> quoteTweets;

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private TweetThread thread;

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

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
        this.deletedAt = new Date();
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

    public Set<Bookmark> getBookmarksBelongTo() {
        return bookmarksBelongTo;
    }

    public void setBookmarksBelongTo(Set<Bookmark> bookmarksBelongTo) {
        this.bookmarksBelongTo = bookmarksBelongTo;
    }

    public void setRetweets(Set<Retweet> retweets) {
        this.retweets = retweets;
    }

    public void setTweetReplies(Set<TweetReply> tweetReplies) {
        this.tweetReplies = tweetReplies;
    }

    public void setQuoteTweets(Set<QuoteTweet> quoteTweets) {
        this.quoteTweets = quoteTweets;
    }

    public void setThread(TweetThread tweetThread) {
        this.thread = tweetThread;
    }

    public Set<Retweet> getRetweets() {
        return retweets;
    }

    public Set<TweetReply> getTweetReplies() {
        return tweetReplies;
    }

    public Set<QuoteTweet> getQuoteTweets() {
        return quoteTweets;
    }

    public TweetThread getThread() {
        return thread;
    }
}
