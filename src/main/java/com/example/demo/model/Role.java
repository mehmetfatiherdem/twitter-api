package com.example.demo.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    //TODO: find a way to make this enum or hashmap
    @Column(name = "name")
    private String name = "normal";

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
