package com.example.backend.controller;

import com.example.backend.dto.board.BoardDetailResponseDto;
import com.example.backend.dto.board.BoardListResponseDto;
import com.example.backend.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
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

    //게시글 수정

    //게시글 삭제
}
