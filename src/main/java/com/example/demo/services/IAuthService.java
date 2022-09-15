package com.example.demo.services;

import com.example.demo.dto.UserLogInDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.model.User;


public interface IAuthService {
    User signUp(UserRegisterDTO dto);
    String signIn(UserLogInDTO dto);
}
