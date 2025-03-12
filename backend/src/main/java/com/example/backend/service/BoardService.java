package com.example.backend.service;

import com.example.backend.dto.board.BoardDetailResponseDto;
import com.example.backend.dto.board.BoardListResponseDto;
import com.example.backend.entity.Board;
import com.example.backend.repository.BoardRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final HttpSession session;
    public BoardService(BoardRepository boardRepository, HttpSession session) {
        this.boardRepository = boardRepository;
        this.session = session;
    }

    //리스트 조회
    public List<BoardListResponseDto> getBoardList() {
         List<Board> boardList = boardRepository.findAllByOrderByBoardCreatedAtDesc();
         return boardList.stream().map(board -> new BoardListResponseDto(
                 board.getBoardId(),
                 board.getBoardTitle(),
                 board.getBoardCreatedAt(),
                 board.getUser().getUserName(),
                 board.getUser().getUserProfileImgUrl(),
                 board.getLikes().size(),
                 board.getCommentList().size(),
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
            if (userId != null && board.getUser().getUserId() == userId) {
                isMyBoard = true;
            }
            boolean isLiked = board.getLikes().stream()
                    .anyMatch(like -> like.getUser().getUserId().equals(userId));
            return new BoardDetailResponseDto(board, isMyBoard, isLiked, "게시글 조회에 성공하였습니다.");
        }
        return new BoardDetailResponseDto("게시글을 조회하는 데 실패하였습니다.");
    }


    //게시글 작성

    //게시글 수정

    //게시글 삭제
}
