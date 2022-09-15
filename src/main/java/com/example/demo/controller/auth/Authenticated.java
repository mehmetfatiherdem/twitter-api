package com.example.demo.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticated")
public class Authenticated {


    @GetMapping("/")
    public String test(){
        return "authenticated..";
    }
}
