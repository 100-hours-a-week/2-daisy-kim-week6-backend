package com.example.backend.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Integer commentId;
    private String commentContent;
    private String userName;
    private String userProfileImgUrl;
    private LocalDateTime commentCreatedAt;
    private boolean isMyComment;

    private String message;

    public CommentResponseDto(String commentContent, LocalDateTime commentCreatedAt, String userName, String userProfileImgUrl, boolean isMyComment) {
        this.commentContent = commentContent;
        this.commentCreatedAt = commentCreatedAt;
        this.userName = userName;
        this.userProfileImgUrl = userProfileImgUrl;
        this.isMyComment = isMyComment;
    }
}
