package com.example.demo.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    public static String hash(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String hashed = encoder.encode(password);
        return hashed;
    }
}
