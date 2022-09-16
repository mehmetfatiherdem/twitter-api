package com.example.demo.controller.auth;

import com.example.demo.dto.UserLogInDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.exception.auth.UserAlreadyExistsException;
import com.example.demo.exception.auth.UserDoesNotExistException;
import com.example.demo.model.User;
import com.example.demo.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class Auth {

    @Autowired
    private IAuthService authService;

    @PostMapping("/api/auth/signup")
    public String signUp(@RequestBody @Valid UserRegisterDTO dto) {
        try {

            User user = authService.signUp(dto);

            return user.toString();

        } catch (UserAlreadyExistsException ex) {

        }

        return "something went wrong";
    }

    @PostMapping("/api/auth/signin")
    public String signIn(@RequestBody @Valid UserLogInDTO dto) {
       try {
           return authService.signIn(dto);

       }catch (UserDoesNotExistException ex){

       }

        return "sign in page";
    }
}
