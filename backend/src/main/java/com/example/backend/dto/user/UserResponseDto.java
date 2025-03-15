package com.example.backend.dto.user;

public class UserResponseDto {
    private String name;
    private String email;
    private String imageUrl;
    private String message;
    public UserResponseDto(String userName, String userEmail, String userProfileImgUrl, String message) {
        this.name = userName;
        this.email = userEmail;
        this.imageUrl = userProfileImgUrl;
        this.message = message;
    }
    public UserResponseDto(String message) {
        this.message = message;
    }
    public String getUserName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getMessage() {
        return message;
    }
}
