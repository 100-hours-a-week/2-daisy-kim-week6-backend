package com.example.backend.dto.user;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private Integer id;
    private String message;
    public UserLoginResponseDto(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
