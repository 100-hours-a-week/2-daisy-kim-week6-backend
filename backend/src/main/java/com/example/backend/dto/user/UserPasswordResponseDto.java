package com.example.backend.dto.user;

import lombok.Getter;

public class UserPasswordResponseDto {
    @Getter
    private String password;

    @Getter
    private String message;

    public UserPasswordResponseDto(String password, String message) {
        this.password = password;
        this.message = message;
    }
    public UserPasswordResponseDto(String message) {
        this.message = message;
    }
}
