package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TrendTopic")
public class TrendTopic {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "trend_topic")
    private String topic;

    @Column(name = "country")
    private String country;

    @ManyToMany(mappedBy = "trendTopics")
    Set<TrendTopicList> trendTopicListsBelongTo;

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

    public TrendTopic(){}

    public UUID getId(){
        return this.id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Set<TrendTopicList> getTrendTopicListsBelongTo() {
        return trendTopicListsBelongTo;
    }

    public void setTrendTopicListsBelongTo(Set<TrendTopicList> trendTopicListsBelongTo) {
        this.trendTopicListsBelongTo = trendTopicListsBelongTo;
    }
}
