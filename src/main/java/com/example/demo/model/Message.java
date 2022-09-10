package com.example.demo.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Message")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Message {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(name = "text")
    private String text;

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


    public Message(){}



    public UUID getId(){
        return this.id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
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


}
