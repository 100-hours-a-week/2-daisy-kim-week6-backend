package com.example.backend.dto;

public class UserDto {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfileImgUrl;
    public UserDto(int userId, String userName, String userPassword, String userEmail, String userProfileImgUrl) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userProfileImgUrl = userProfileImgUrl;
    }
    public int getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserProfileImgUrl() {
        return userProfileImgUrl;
    }
}
