package com.example.demo.exception.auth;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String info){
        super("User with the info of "
                + info + " does not exist!");
    }
}
