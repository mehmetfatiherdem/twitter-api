package com.example.demo.exception.auth;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(){
        super("Password you entered is wrong!");
    }
}
