package com.example.demo.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

//TODO: check all the table name defaults and sql delete queries
@Entity(name = "FollowingList")
@SQLDelete(sql = "UPDATE FollowingList SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class FollowingList {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "followingList")
    private User owner;

    @ManyToMany(mappedBy = "followingListsBelongTo")
    Set<User> followings;


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

    public FollowingList(){}

    public UUID getId(){ return this.id; }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<User> followings) {
        this.followings = followings;
    }

    public boolean isDeleted() {
        return isDeleted;
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
