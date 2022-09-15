package com.example.demo.controller.auth;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.exception.auth.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class Auth {

    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid UserRegisterDTO dto) {
        try {

            User user = authService.signUp(dto);

            return user.toString();

        } catch (UserAlreadyExistsException ex) {

        }

        return "something went wrong";
    }

    //FIXME: change this to POST
    @GetMapping("/signin")
    public String signIn() {
       return "sign in page";
    }
}
