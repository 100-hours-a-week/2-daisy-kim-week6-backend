package com.example.backend.controller;

import com.example.backend.dto.board.BoardDetailResponseDto;
import com.example.backend.dto.board.BoardListResponseDto;
import com.example.backend.dto.board.BoardPostRequestDto;
import com.example.backend.service.BoardLikeService;
import com.example.backend.service.BoardService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    public BoardDetailResponseDto writeBoard(
            @RequestPart (value = "title") String title,
            @RequestPart(value = "content") String content,
            @RequestPart(value = "imageUrl", required = false) MultipartFile imageUrl
    ) throws IOException {
        String imagePath = null;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String uploadDir = "/Users/kdh/Desktop/hw_image/"; // 저장 폴더
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + imageUrl.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            imageUrl.transferTo(destination);

            imagePath = uploadDir + fileName;
        }
        BoardPostRequestDto boardPostRequestDto = new BoardPostRequestDto(title, content, imagePath);
        return boardService.writeBoard(boardPostRequestDto);
    }

    //게시글 수정
    @PatchMapping("/{boardId}")
    public BoardDetailResponseDto editBoard(
            @PathVariable Integer boardId,
            @RequestPart (value = "title") String title,
            @RequestPart (value = "content") String content,
            @RequestPart(value = "imageUrl", required = false) MultipartFile imageUrl
    ) throws IOException {
        String imagePath = null;

        // 이미지 저장 로직
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String uploadDir = "/Users/kdh/Desktop/hw_image/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + imageUrl.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            imageUrl.transferTo(destination);

            imagePath = uploadDir + fileName;
        }

        // 이미지 경로를 DTO에 설정
        BoardPostRequestDto boardPostRequestDto = new BoardPostRequestDto(title, content, imagePath);
        return boardService.updateBoard(boardId, boardPostRequestDto);
    }


    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public BoardDetailResponseDto deleteBoard(@PathVariable Integer boardId) {
        return boardService.deleteBoard(boardId);
    }

    @PostMapping("/{boardId}/like")
    public BoardDetailResponseDto postLike(@PathVariable Integer boardId) {
        return boardLikeService.postLike(boardId);
    }

    @DeleteMapping("/{boardId}/like-delete")
    public BoardDetailResponseDto deleteLike(@PathVariable Integer boardId) {
        return boardLikeService.deleteLike(boardId);
    }
}
