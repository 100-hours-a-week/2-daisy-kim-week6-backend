package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.comment.CommentRequestDto;
import com.example.backend.dto.comment.CommentResponseDto;
import com.example.backend.entity.Board;
import com.example.backend.entity.Comment;
import com.example.backend.entity.User;
import com.example.backend.repository.BoardRepository;
import com.example.backend.repository.CommentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final HttpSession httpSession;
    private final BoardRepository boardRepository;

    public CommentService(CommentRepository commentRepository, HttpSession httpSession, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.httpSession = httpSession;
        this.boardRepository = boardRepository;
    }
    //답글 목록 조회
    public List<CommentResponseDto> getAllComments(Integer boardId) {
        List<Comment> commentList = commentRepository.findByBoard_Id(boardId);
        Integer userId = (Integer) httpSession.getAttribute("userId");

        return commentList.stream().map(comment -> new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUser().getName(),
                comment.getUser().getImageUrl(),
                comment.getUser().getId().equals(userId),
                "답글 목록 조회 성공"
        ))
                .collect(Collectors.toList());
    }

    //답글 게시
    public ResponseDto createComment(Integer boardId, CommentRequestDto commentRequestDto) {
        User user = (User) httpSession.getAttribute("user");
        Board board = boardRepository.findById(boardId).orElse(null);
        if (user == null) {
            return new ResponseDto("로그인하세요.");
        }
        Comment newComment = new Comment(
                commentRequestDto.getContent(),
                user,
                board,
                LocalDateTime.now()
        );
        commentRepository.save(newComment);
        Objects.requireNonNull(board).increaseCommentCount();
        boardRepository.save(board);
        return new ResponseDto("답글을 성공적으로 생성하였습니다.");
    }

    //답글 수정
    public ResponseDto updateComment(Integer commentId, CommentRequestDto commentRequestDto) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return new ResponseDto("로그인하세요.");
        }
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return new ResponseDto("지울 수 있는 답글이 없습니다.");
        }
        comment.setContent(commentRequestDto.getContent());
        return new ResponseDto("답글을 성공적으로 삭제하였습니다.");
    }

    //답글 삭제
    public ResponseDto deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
        Board board = boardRepository.findById(commentId).orElse(null);
        Objects.requireNonNull(board).decreaseCommentCount();
        boardRepository.save(board);
        return new ResponseDto("답글을 성공적으로 삭제하였습니다.");
    }

}
