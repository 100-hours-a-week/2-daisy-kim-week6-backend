package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardLikes> likes;
}
