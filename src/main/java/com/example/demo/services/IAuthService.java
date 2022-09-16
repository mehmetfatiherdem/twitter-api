package com.example.demo.services;

import com.example.demo.dto.UserLogInDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.model.User;
import com.example.demo.payload.JWTAuthResponse;


public interface IAuthService {
    User signUp(UserRegisterDTO dto);
    JWTAuthResponse signIn(UserLogInDTO dto);
}
