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
    private Integer id;

    @Setter
    @Column(nullable = false, length = 10)
    private String name;

    @Setter
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Lob
    @Setter
    @Column(nullable = false)
    private String imageUrl;

    public User(String name, String password, String email, String imageUrl) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<BoardLikes> likes;
}
