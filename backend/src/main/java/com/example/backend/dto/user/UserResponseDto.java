package com.example.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private String imageUrl;
    private String message;

    public UserResponseDto(String message) {
        this.message = message;
    }

    public UserResponseDto(String name, String imageUrl, String message) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.message = message;
    }

    public UserResponseDto(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
