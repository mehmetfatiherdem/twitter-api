package com.example.demo.exception.tweet;

public class TweetCouldNotBeCreatedException extends RuntimeException{
    public TweetCouldNotBeCreatedException(){
        super("Creating tweet failed");
    }
}
