package com.example.backend.dto.board;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {
    private Integer boardId;
    private String boardTitle;
    private LocalDateTime boardCreatedAt;
    private String userName;
    private String userProfileImageUrl;
    private Integer boardLikeCount;
    private Integer boardCommentCount;
    private Integer boardViewCount;

    public BoardListResponseDto() {}

    public BoardListResponseDto(Integer boardId, String boardTitle, LocalDateTime boardCreatedAt, String userName, String userProfileImgUrl, int boardLikeCount, int boardCommentCount, Integer boardViewCount) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardCreatedAt = boardCreatedAt;
        this.userName = userName;
        this.userProfileImageUrl = userProfileImgUrl;
        this.boardLikeCount = boardLikeCount;
        this.boardCommentCount = boardCommentCount;
        this.boardViewCount = boardViewCount;
    }
}
