package com.example.demo.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Auth {

    @GetMapping("/home")
    public String home(){
        return "This is the home page";
    }
}
