package com.example.backend.dto.user;

import lombok.Getter;

public class UserPasswordResponseDto {
    @Getter
    private String userPassword;

    @Getter
    private String message;

    public UserPasswordResponseDto(String userPassword, String message) {
        this.userPassword = userPassword;
        this.message = message;
    }
    public UserPasswordResponseDto(String message) {
        this.message = message;
    }
}
