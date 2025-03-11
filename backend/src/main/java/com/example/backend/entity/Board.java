package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name="boards")
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @Column(length = 26, nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;
    private String boardContentImgUrl;
    private LocalDateTime boardCreatedAt;
    private LocalDateTime boardUpdatedAt;
    private Integer viewCount;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardLikes> likes;
}
