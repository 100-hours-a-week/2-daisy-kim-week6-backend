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
    private Integer id;

    @Column(length = 26, nullable = false)
    @Setter
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @Setter
    private String content;

    @Setter
    private String imageUrl;

    private LocalDateTime createdAt;
    @Column(columnDefinition = "int default 0")
    private Integer viewCount = 0;
    @Column(columnDefinition = "int default 0")
    private Integer likeCount = 0;
    @Column(columnDefinition = "int default 0")
    private Integer commentCount = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Board(String title, String content, String imageUrl, User user, LocalDateTime now) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = now;
        this.user = user;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
    public void increaseLikeCount() {
        this.likeCount++;
    }
    public void increaseCommentCount() {
        this.commentCount++;
    }
    public void decreaseCommentCount() {
        this.commentCount--;
    }
    public void decreaseLikeCount() {
        this.likeCount--;
    }
}
