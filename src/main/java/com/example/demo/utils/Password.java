package com.example.demo.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    public static String hash(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String hashed = encoder.encode(password);
        return hashed;
    }

    public static Boolean matchPassword(String password, String hash){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hash);
    }
    
}
