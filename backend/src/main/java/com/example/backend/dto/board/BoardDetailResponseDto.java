package com.example.backend.dto.board;

import com.example.backend.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponseDto {
    private Integer boardId;
    private String boardTitle;
    private String boardContent;
    private String boardContentImgUrl;
    private LocalDateTime boardCreatedAt;
    private Integer boardLikeCount;
    private Integer boardCommentCount;
    private Integer boardViewCount;
    private Integer userId;
    private String userName;
    private String userProfileImgUrl;

    @Setter
    private boolean isMyBoard;
    @Setter
    private boolean isLike;

    private String message;

    public BoardDetailResponseDto(Board board, boolean isMyBoard, boolean isLike, String message) {
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.boardContentImgUrl = board.getBoardContentImgUrl();
        this.boardCreatedAt = board.getBoardCreatedAt();
        this.boardLikeCount = board.getLikes().size();
        this.boardCommentCount = board.getCommentList().size();
        this.boardViewCount = board.getViewCount();
        this.userId = board.getUser().getUserId();
        this.userName = board.getUser().getUserName();
        this.userProfileImgUrl = board.getUser().getUserProfileImgUrl();
        this.isMyBoard = isMyBoard;
        this.isLike = isLike;
        this.message = message;
    }
    public BoardDetailResponseDto() {}
    public BoardDetailResponseDto(String message) {
        this.message = message;
    }
}
