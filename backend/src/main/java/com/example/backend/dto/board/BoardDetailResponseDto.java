package com.example.backend.dto.board;

import com.example.backend.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponseDto {
    private Integer id;
    private String title;
    private String content;
    private String boardImageUrl;
    private LocalDateTime createdAt;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private Integer userId;
    private String userName;
    private String userProfileImgUrl;

    @Setter
    private boolean isMyBoard;
    @Setter
    private boolean isLike;

    private String message;

    public BoardDetailResponseDto(Board board, boolean isMyBoard, boolean isLike, String message) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardImageUrl = board.getImageUrl();
        this.createdAt = board.getCreatedAt();
        this.likeCount = board.getLikeCount();
        this.commentCount = board.getCommentCount();
        this.viewCount = board.getViewCount();
        this.userId = board.getUser().getId();
        this.userName = board.getUser().getName();
        this.userProfileImgUrl = board.getUser().getImageUrl();
        this.isMyBoard = isMyBoard;
        this.isLike = isLike;
        this.message = message;
    }
    public BoardDetailResponseDto() {}
    public BoardDetailResponseDto(String message) {
        this.message = message;
    }
}
