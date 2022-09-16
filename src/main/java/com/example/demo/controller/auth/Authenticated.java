package com.example.demo.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authenticated {


    @GetMapping("/api/authenticated")
    public String test(){
        return "authenticated..";
    }
}
