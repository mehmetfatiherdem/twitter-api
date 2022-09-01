package com.example.demo.model;

import com.example.demo.utils.Password;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;



@Entity(name = "user")
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
}
