package com.example.backend.dto;

public class UserLoginResponseDto {
    private Integer userId;
    private String message;
    public UserLoginResponseDto(Integer userId, String message) {
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
