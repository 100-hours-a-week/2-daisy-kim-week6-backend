package com.example.backend.dto;

public class UserSigninResponseDto {
    private Integer userId;
    private String message;
    public UserSigninResponseDto(Integer userId, String message) {
        this.userId = userId;
        this.message = message;
    }
    public Integer getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
