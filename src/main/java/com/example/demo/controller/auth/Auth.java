package com.example.demo.controller.auth;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.exception.auth.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Auth {

    @Autowired
    private IAuthService authService;

    @GetMapping("/home")
    public String home(){
        return "This is the home page";
    }

    @PostMapping("/auth/signup")
    public String signUp(@RequestBody @Valid UserRegisterDTO dto) {
        try {

            User user = authService.signUp(dto);

            return user.toString();

        } catch (UserAlreadyExistsException ex) {

        }

        return "something went wrong";
    }
}
