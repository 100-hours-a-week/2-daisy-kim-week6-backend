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

import java.io.File;
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
        List<Comment> commentList = commentRepository.findByBoard_IdOrderByCreatedAtDesc(boardId);
        Integer userId = (Integer) httpSession.getAttribute("userId");


        return commentList.stream().map(comment -> new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUser().getName(),
                "/uploads/" + new File(comment.getUser().getImageUrl()).getName(),
                comment.getUser().getId().equals(userId),
                commentRepository.countByBoard_Id(boardId),
                "답글 목록 조회 성공"
        ))
                .collect(Collectors.toList());
    }

    //답글 게시
    public CommentResponseDto createComment(Integer boardId, CommentRequestDto commentRequestDto) {
        User user = (User) httpSession.getAttribute("user");
        Board board = boardRepository.findById(boardId).orElse(null);
        if (user == null) {
            return new CommentResponseDto("로그인하세요.");
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
        return new CommentResponseDto(commentRepository.countByBoard_Id(boardId), "답글을 성공적으로 생성하였습니다.");
    }

    //답글 수정
    public ResponseDto updateComment(Integer commentId, CommentRequestDto commentRequestDto) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return new ResponseDto("로그인하세요.");
        }
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return new ResponseDto("수정할 답글을 선택하세요.");
        }
        comment.setContent(commentRequestDto.getContent());
        commentRepository.save(comment);
        return new ResponseDto("답글을 성공적으로 수정하였습니다.");
    }

    //답글 삭제
    public CommentResponseDto deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
        Board board = comment.getBoard(); // 댓글에서 게시글 ID 가져오기
        commentRepository.deleteById(commentId); // 댓글 삭제
        board.decreaseCommentCount(); // 댓글 수 감소
        boardRepository.save(board); // 변경된 board 저장

        return new CommentResponseDto(commentRepository.countByBoard_Id(board.getId()), "답글을 성공적으로 삭제하였습니다.");

    }

}
