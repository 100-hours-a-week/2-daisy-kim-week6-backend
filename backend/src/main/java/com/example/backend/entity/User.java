package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Setter
    @Column(nullable = false, length = 10)
    private String userName;

    @Setter
    @Column(nullable = false, length = 20)
    private String userPassword;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Setter
    @Column(nullable = false)
    private String userProfileImgUrl;

    public User(String userName, String userPassword, String userEmail, String userProfileImgUrl) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userProfileImgUrl = userProfileImgUrl;
    }
    public User() {}
}
