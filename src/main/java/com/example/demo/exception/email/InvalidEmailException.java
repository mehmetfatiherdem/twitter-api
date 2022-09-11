package com.example.demo.exception.email;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(){
        super("The email you entered is invalid");
    }
}
