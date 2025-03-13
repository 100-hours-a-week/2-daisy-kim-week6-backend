package com.example.backend.repository;

import com.example.backend.entity.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLikes, Integer> {
    Optional<BoardLikes> findByBoard_BoardIdAndUser_UserId(Integer boardId, Integer userId);
}
