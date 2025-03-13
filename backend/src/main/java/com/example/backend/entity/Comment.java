package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(nullable = false)
    @Setter
    private String commentContent;

    private LocalDateTime commentCreatedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    public Comment(String commentContent, User user, Board board, LocalDateTime commentCreatedAt) {
        this.commentContent = commentContent;
        this.user = user;
        this.board = board;
        this.commentCreatedAt = commentCreatedAt;
    }
}
