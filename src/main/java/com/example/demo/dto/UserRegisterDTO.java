package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class UserRegisterDTO {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
