package com.example.demo.exception.jwt;

import org.springframework.http.HttpStatus;

public class JWTException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public JWTException(HttpStatus status, String message){
        super(message);
        this.status = status;
        this.message = message;
    }
}
