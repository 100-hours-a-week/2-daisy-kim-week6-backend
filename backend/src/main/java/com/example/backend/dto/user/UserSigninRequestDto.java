package com.example.backend.dto.user;

import lombok.Getter;

@Getter
public class UserSigninRequestDto {
    private String name;
    private String password;
    private String passwordConfirm;
    private String email;
    private String imageUrl;
}
