package com.example.backend.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private String userName;
    private String userImageUrl;
    private boolean isMyComment;
    private Integer count;
    private String message;

    public CommentResponseDto(Integer commentCount, String message) {
        this.count = commentCount;
        this.message = message;
    }

    public CommentResponseDto(String message) {
        this.message = message;
    }
}
