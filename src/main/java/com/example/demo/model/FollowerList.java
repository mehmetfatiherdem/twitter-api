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
public class FollowerList {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "followerList")
    private User owner;

    @ManyToMany(mappedBy = "followerListsBelongTo")
    Set<User> followers;


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

    public FollowerList(){}

    public UUID getId(){ return this.id; }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
        this.deletedAt = new Date();
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
