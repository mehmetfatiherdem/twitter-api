package com.example.demo.exception.auth;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String email){
        super("User with the email address of "
                + email + " does not exist!");
    }
}
