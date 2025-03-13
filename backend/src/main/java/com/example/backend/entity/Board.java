package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Setter
    private String boardTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Setter
    private String boardContent;

    @Setter
    private String boardContentImgUrl;

    private LocalDateTime boardCreatedAt;
    @Column(columnDefinition = "int default 0")
    private Integer viewCount = 0;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardLikes> likes;

    public Board(String boardTitle, String boardContent, String boardContentImgUrl, User user, LocalDateTime now) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardContentImgUrl = boardContentImgUrl;
        this.boardCreatedAt = now;
        this.user = user;
        likes = new ArrayList<>();
        commentList = new ArrayList<>();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}
