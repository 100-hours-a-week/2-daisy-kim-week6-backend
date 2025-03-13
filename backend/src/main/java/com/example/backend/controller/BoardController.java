package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.board.BoardDetailResponseDto;
import com.example.backend.dto.board.BoardListResponseDto;
import com.example.backend.dto.board.BoardPostRequestDto;
import com.example.backend.service.BoardLikeService;
import com.example.backend.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BoardLikeService boardLikeService;

    public BoardController(BoardService boardService, BoardLikeService boardLikeService) {
        this.boardService = boardService;
        this.boardLikeService = boardLikeService;
    }

    @GetMapping("")
    public List<BoardListResponseDto> getBoardList() {
        return boardService.getBoardList();
    }

    //게시글 조회
    @GetMapping("/{boardId}")
    public BoardDetailResponseDto getBoardDetail(@PathVariable Integer boardId) {
        return boardService.getBoardDetail(boardId);
    }

    //게시글 작성
    @PostMapping("")
    public BoardDetailResponseDto writeBoard(@RequestBody BoardPostRequestDto boardPostRequestDto) {
        return boardService.writeBoard(boardPostRequestDto);
    }

    //게시글 수정
    @PatchMapping("/{boardId}")
    public BoardDetailResponseDto updateBoard(@PathVariable Integer boardId, BoardPostRequestDto boardPostRequestDto) {
        return boardService.updateBoard(boardId, boardPostRequestDto);
    }

    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public BoardDetailResponseDto deleteBoard(@PathVariable Integer boardId) {
        return boardService.deleteBoard(boardId);
    }

    @PostMapping("/{boardId}/like")
    public ResponseDto postLike(@PathVariable Integer boardId) {
        return boardLikeService.postLike(boardId);
    }

    @DeleteMapping("/{boardId}/like-delete")
    public ResponseDto deleteLike(@PathVariable Integer boardId) {
        return boardLikeService.deleteLike(boardId);
    }
}
