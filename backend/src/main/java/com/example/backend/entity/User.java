package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userProfileImgUrl;

    public User(String userNme, String userPassword, String userEmail, String userProfileImgUrl) {
        this.userName = userNme;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userProfileImgUrl = userProfileImgUrl;
    }

    public User() {}

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
