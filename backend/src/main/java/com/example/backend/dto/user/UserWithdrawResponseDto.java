package com.example.backend.dto.user;

public class UserWithdrawResponseDto {
    private String message;
    public UserWithdrawResponseDto(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
