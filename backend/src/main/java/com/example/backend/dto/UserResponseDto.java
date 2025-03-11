package com.example.backend.dto;

public class UserResponseDto {
    private String userName;
    private String userEmail;
    private String userProfileImgUrl;
    private String message;
    public UserResponseDto(String userName, String userEmail, String userProfileImgUrl, String message) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userProfileImgUrl = userProfileImgUrl;
        this.message = message;
    }
    public UserResponseDto(String message) {
        this.message = message;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserProfileImgUrl() {
        return userProfileImgUrl;
    }
    public String getMessage() {
        return message;
    }
}
