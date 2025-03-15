package com.example.backend.dto.user;

public class UserSigninResponseDto {
    private Integer id;
    private String message;
    public UserSigninResponseDto(Integer userId, String message) {
        this.id = userId;
        this.message = message;
    }
    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
