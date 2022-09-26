package com.example.demo.model;

import com.example.demo.utils.Password;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "`user`")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "provider")
    private String provider = "local";

    @Column(name = "provider_id")
    private String providerId;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> messagesSent;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Message> messagesReceived;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmark_id", referencedColumnName = "id")
    private Bookmark bookmark;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_list_id", referencedColumnName = "id")
    private MessageList messageList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Retweet> retweets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TweetReply> tweetReplies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Tweet> tweets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TweetThread> threads;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<QuoteTweet> quoteTweets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trend_topic_list_id", referencedColumnName = "id")
    private TrendTopicList trendTopicList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "follower_list_id", referencedColumnName = "id")
    private FollowerList followerList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "following_list_id", referencedColumnName = "id")
    private FollowingList followingList;

    @ManyToMany
    @JoinTable(name = "follower_list_belong_to", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "follower_list_id"))
    Set<FollowerList> followerListsBelongTo;

    @ManyToMany
    @JoinTable(name = "following_list_belong_to", joinColumns = @JoinColumn(name = "following_id"), inverseJoinColumns = @JoinColumn(name = "following_list_id"))
    Set<FollowingList> followingListsBelongTo;

    @ManyToMany(mappedBy = "messageListUsers")
    Set<MessageList> messageListsBelongTo;
    
    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;

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

    public User(){}


    public User(String name, String lastName, String email, String password){

        this.name = name;
        this.lastName = lastName;
        this.email = email;

        String hashedPassword = Password.hash(password);

        this.password = hashedPassword;
    }


    //TODO: find a better JSON serialize method
    @Override
    public String toString(){
        return "{\n" +
                "\"id\": \"" + this.id + "\",\n" +
                "\"name\": \"" + this.name + "\",\n" +
                "\"lastname\": \"" + this.lastName + "\",\n" +
                "\"email\": \"" + this.email + "\"\n" +
                "}"
                ;
    }


    public UUID getId(){
        return this.id;
    }


    public Set<FollowingList> getFollowingListsBelongTo() {
        return followingListsBelongTo;
    }

    public void setFollowingListsBelongTo(Set<FollowingList> followingListsBelongTo) {
        this.followingListsBelongTo = followingListsBelongTo;
    }

    public FollowerList getFollowerList() {
        return followerList;
    }

    public void setFollowerList(FollowerList followerList) {
        this.followerList = followerList;
    }

    public Set<FollowerList> getFollowerListsBelongTo() {
        return followerListsBelongTo;
    }

    public void setFollowerListsBelongTo(Set<FollowerList> followerListsBelongTo) {
        this.followerListsBelongTo = followerListsBelongTo;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email){
            this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        String hashedPassword = Password.hash(password);
        this.password = hashedPassword;

    }

    @JsonIgnore
    public String getProvider() {
        return provider;
    }

    @JsonProperty
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonIgnore
    public String getProviderId() {
        return providerId;
    }

    @JsonProperty
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @JsonIgnore
    public List<Message> getMessagesSent() {
        return messagesSent;
    }

    @JsonProperty
    public void setMessagesSent(List<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    @JsonIgnore
    public List<Message> getMessagesReceived() {
        return messagesReceived;
    }

    @JsonProperty
    public void setMessagesReceived(List<Message> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    @JsonIgnore
    public boolean isDeleted() {
        return isDeleted;
    }

    @JsonProperty
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
        this.deletedAt = new Date();
    }

    @JsonIgnore
    public Date getDeletedAt() {
        return deletedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public Bookmark getBookmark() {
        return bookmark;
    }

    @JsonProperty
    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    @JsonIgnore
    public MessageList getMessageList() {
        return messageList;
    }

    @JsonProperty
    public void setMessageList(MessageList messageList) {
        this.messageList = messageList;
    }

    public Set<Retweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(Set<Retweet> retweets) {
        this.retweets = retweets;
    }

    public void setTweetReplies(Set<TweetReply> tweetReplies) {
        this.tweetReplies = tweetReplies;
    }

    public Set<TweetReply> getTweetReplies() {
        return tweetReplies;
    }

    public Set<TweetThread> getThreads() {
        return threads;
    }

    public void setThreads(Set<TweetThread> threads) {
        this.threads = threads;
    }

    public Set<QuoteTweet> getQuoteTweets() {
        return quoteTweets;
    }

    public void setQuoteTweets(Set<QuoteTweet> quoteTweets) {
        this.quoteTweets = quoteTweets;
    }

    @JsonIgnore
    public TrendTopicList getTrendTopicList() {
        return trendTopicList;
    }

    @JsonProperty
    public void setTrendTopicList(TrendTopicList trendTopicList) {
        this.trendTopicList = trendTopicList;
    }

    public FollowingList getFollowingList() {
        return followingList;
    }

    public void setFollowingList(FollowingList followingList) {
        this.followingList = followingList;
    }

    @JsonIgnore
    public Set<MessageList> getMessageListsBelongTo() {
        return messageListsBelongTo;
    }

    @JsonProperty
    public void setMessageListsBelongTo(Set<MessageList> messageListsBelongTo) {
        this.messageListsBelongTo = messageListsBelongTo;
    }

    @JsonIgnore
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    @JsonProperty
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
