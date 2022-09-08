package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "MessageList")
public class MessageList {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "messageList")
    private User owner;

    @ManyToMany
    @JoinTable(name = "message_list_user", joinColumns = @JoinColumn(name = "message_list_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> messageListUsers;

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

    public MessageList(){}

    public UUID getId(){
        return this.id;
    }

    public Set<User> getMessageListUsers() {
        return messageListUsers;
    }

    public void setMessageListUsers(Set<User> messageListUsers) {
        this.messageListUsers = messageListUsers;
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
}
