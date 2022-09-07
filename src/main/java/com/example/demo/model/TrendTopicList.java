package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class TrendTopicList {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(mappedBy = "trendTopicList")
    private User user;

    @ManyToMany
    @JoinTable(name = "ttlist_tt", joinColumns = @JoinColumn(name = "trend_topic_list_id"), inverseJoinColumns = @JoinColumn(name = "trend_topic_id"))
    Set<TrendTopic> trendTopics;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deleted_at;

    @PreUpdate
    @PrePersist
    protected void updateTimeStamps(){
        updatedAt = new Date();
        if(createdAt == null) createdAt = new Date();
    }

}
