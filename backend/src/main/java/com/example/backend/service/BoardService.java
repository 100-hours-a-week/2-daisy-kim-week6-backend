package com.example.backend.service;

import com.example.backend.dto.board.BoardDetailResponseDto;
import com.example.backend.dto.board.BoardListResponseDto;
import com.example.backend.dto.board.BoardPostRequestDto;
import com.example.backend.entity.Board;
import com.example.backend.entity.User;
import com.example.backend.repository.BoardLikeRepository;
import com.example.backend.repository.BoardRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final HttpSession session;
    public BoardService(BoardRepository boardRepository, HttpSession session, BoardLikeRepository boardLikeRepository) {
        this.boardRepository = boardRepository;
        this.boardLikeRepository = boardLikeRepository;
        this.session = session;
    }

    //리스트 조회
    public List<BoardListResponseDto> getBoardList() {
         List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc();
         return boardList.stream().map(board -> new BoardListResponseDto(
                 board.getId(),
                 board.getTitle(),
                 board.getCreatedAt(),
                 board.getUser().getName(),
                 "/uploads/" + new File(board.getUser().getImageUrl()).getName(),
                 board.getLikeCount(),
                 board.getCommentCount(),
                 board.getViewCount()
                 ))
                 .collect(Collectors.toList());
    }

    //상세 조회
    //게시글 조회수 증가 - api 호출될 때마다 1증가해서 반환
    @Transactional
    public BoardDetailResponseDto getBoardDetail(Integer boardId) {
        Optional<Board> boardEntity = boardRepository.findById(boardId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (boardEntity.isPresent()) {
            Board board = boardEntity.get();
            board.increaseViewCount();
            boolean isMyBoard = false;
            if (userId != null && board.getUser().getId().equals(userId)) {
                isMyBoard = true;
            }
            boolean isLiked = boardLikeRepository.existsByBoardIdAndUserId(board.getId(), userId);
            return new BoardDetailResponseDto(board, isMyBoard, isLiked, "게시글 조회에 성공하였습니다.");
        }
        return new BoardDetailResponseDto("게시글을 조회하는 데 실패하였습니다.");
    }

    //게시글 작성
    public BoardDetailResponseDto writeBoard(BoardPostRequestDto boardPostRequestDto) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new BoardDetailResponseDto("로그인이 필요합니다.");
        }
        if (boardPostRequestDto.getTitle().length() > 26) {
            return new BoardDetailResponseDto("제목은 최대 26글자입니다.");
        }
        Board newBoard = new Board(
                boardPostRequestDto.getTitle(),
                boardPostRequestDto.getContent(),
                boardPostRequestDto.getImageUrl(),
                user,
                LocalDateTime.now()
                );
        boardRepository.save(newBoard);
        return new BoardDetailResponseDto(newBoard, true, false, "게시글을 성공적으로 업로드하였습니다.");
    }

    //게시글 수정
    @Transactional
    public BoardDetailResponseDto updateBoard(Integer boardId, BoardPostRequestDto boardPostRequestDto) throws IOException {
        Optional<Board> boardEntity = boardRepository.findById(boardId);
        if (boardEntity.isPresent()) {
            User user = (User) session.getAttribute("user");
            Board board = boardEntity.get();
            if (user == null) {
                return new BoardDetailResponseDto("로그인한 사용자만 접근할 수 있습니다.");
            }
            if (!board.getUser().getId().equals(user.getId())) {
                return new BoardDetailResponseDto("본인의 게시글만 수정할 수 있습니다.");
            }
            boolean isLiked = boardLikeRepository.existsByBoardIdAndUserId(board.getId(), user.getId());
            board.setTitle(boardPostRequestDto.getTitle());
            board.setContent(boardPostRequestDto.getContent());
            if (boardPostRequestDto.getImageUrl() != null) {
                board.setImageUrl(boardPostRequestDto.getImageUrl());
            }
            return new BoardDetailResponseDto(board, true, isLiked, "게시글을 성공적으로 수정하였습니다.");
        }
        return new BoardDetailResponseDto("해당 게시글이 존재하지 않습니다.");
    }


    //게시글 삭제
    public BoardDetailResponseDto deleteBoard(Integer boardId) {
        Optional<Board> boardEntity = boardRepository.findById(boardId);
        if (boardEntity.isPresent()) {
            boardRepository.deleteById(boardId);
            return new BoardDetailResponseDto("게시글이 성공적으로 삭제되었습니다.");
        }
        return new BoardDetailResponseDto("게시글 삭제에 실패하였습니다.");
    }
}
