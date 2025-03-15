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
    private String userProfileImgUrl;
    private boolean isMyComment;
    private String message;
}
