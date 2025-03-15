package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.entity.Board;
import com.example.backend.entity.BoardLikes;
import com.example.backend.entity.User;
import com.example.backend.repository.BoardLikeRepository;
import com.example.backend.repository.BoardRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardLikeService {
    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;
    private final HttpSession session;
    public BoardLikeService(BoardLikeRepository boardLikeRepository, BoardRepository boardRepository, HttpSession session) {
        this.boardLikeRepository = boardLikeRepository;
        this.boardRepository = boardRepository;
        this.session = session;
    }
    //좋아요 등록
    @Transactional
    public ResponseDto postLike(Integer boardId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseDto("로그인하세요.");
        }
        if (boardLikeRepository.existsByBoardIdAndUserId(boardId, user.getId())) {
            return new ResponseDto("좋아요를 이미 등록했습니다.");
        }

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        BoardLikes boardLike = new BoardLikes(user, board);
        boardLikeRepository.save(boardLike);
        board.increaseLikeCount();
        boardRepository.save(board);

        return new ResponseDto("좋아요 등록 성공");
    }
    //좋아요 취소
    @Transactional
    public ResponseDto deleteLike(Integer boardId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseDto("로그인하세요.");
        }
        Optional<BoardLikes> boardLikeEntity = boardLikeRepository.findByBoard_IdAndUser_Id(boardId, user.getId());
        if (boardLikeEntity.isPresent()) {
            BoardLikes boardLike = boardLikeEntity.get();
            Board board = boardLike.getBoard();

            board.decreaseLikeCount();
            boardRepository.save(board);
            boardLikeRepository.delete(boardLike);
            return new ResponseDto("좋아요를 성공적으로 취소했습니다.");
        }
        return new ResponseDto("좋아요 취소 실패");
    }
}
