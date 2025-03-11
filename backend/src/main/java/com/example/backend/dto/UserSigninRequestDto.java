package com.example.backend.dto;

public class UserSigninRequestDto {
    private String userName;
    private String userPassword;
    private String userPasswordConfirm;
    private String userEmail;
    private String userProfileImgUrl;
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
    public String getUserPasswordConfirm() {
        return userPasswordConfirm;
    }
}
