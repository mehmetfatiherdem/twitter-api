package com.example.demo.model;

import com.example.demo.utils.Password;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;



@Entity(name = "user")
@SQLDelete(sql = "UPDATE message SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;  //TODO: find a subtle way to check email

    @Column(name = "password")
    private String password;

    private enum UserRole{
        NORMAL,
        ADMIN
    }

    @Column(name = "role")
    private UserRole role = UserRole.NORMAL;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSent;

    @OneToMany(mappedBy = "receiver")
    private List<Message> messagesReceived;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmark_id", referencedColumnName = "id")
    private Bookmark bookmark;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_list_id", referencedColumnName = "id")
    private MessageList messageList;

    @OneToMany(mappedBy = "user")
    private Set<Retweet> retweets;

    @OneToMany(mappedBy = "user")
    private Set<TweetReply> tweetReplies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Thread> threads;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<QuoteTweet> quoteTweets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trend_topic_list_id", referencedColumnName = "id")
    private TrendTopicList trendTopicList;

    //TODO: check all the getter-setters
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

    //TODO: check this soft delete logic
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String hashedPassword = Password.hash(password);
        this.password = hashedPassword;

    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public List<Message> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public List<Message> getMessagesReceived() {
        return messagesReceived;
    }

    public void setMessagesReceived(List<Message> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    //TODO: check for this setDeleted. Does it conflict with soft-delete
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
