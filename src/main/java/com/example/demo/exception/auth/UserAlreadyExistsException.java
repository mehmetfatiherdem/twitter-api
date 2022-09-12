package com.example.demo.exception.auth;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String email){
        super("There is an account with that email address: "
                + email);
    }
}
