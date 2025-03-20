package com.example.backend.dto.user;

import lombok.Getter;

@Getter
public class UserSigninResponseDto {
    private Integer id;
    private String message;
    public UserSigninResponseDto(Integer userId, String message) {
        this.id = userId;
        this.message = message;
    }
}
