package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.comment.CommentRequestDto;
import com.example.backend.dto.comment.CommentResponseDto;
import com.example.backend.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/{boardId}/comment")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //답글 목록
    @GetMapping("")
    public List<CommentResponseDto> getComments(@PathVariable Integer boardId) {
        return commentService.getAllComments(boardId);
    }

    //답글 작성
    @PostMapping("")
    public CommentResponseDto createComment(@PathVariable Integer boardId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(boardId, commentRequestDto);
    }

    //답글 수정
    @PatchMapping("/{commentId}")
    public ResponseDto updateComment(@PathVariable Integer commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(commentId, commentRequestDto);
    }

    //답글 삭제
    @DeleteMapping("/{commentId}")
    public CommentResponseDto deleteComment(@PathVariable Integer commentId) {
        return commentService.deleteComment(commentId);
    }
}
