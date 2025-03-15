package com.example.backend.dto.board;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {
    private Integer id;
    private String title;
    private LocalDateTime createdAt;
    private String userName;
    private String userProfileImageUrl;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;

    public BoardListResponseDto() {}

    public BoardListResponseDto(Integer id, String title, LocalDateTime createdAt, String userName, String userProfileImgUrl, int likeCount, int commentCount, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.userName = userName;
        this.userProfileImageUrl = userProfileImgUrl;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
    }
}
