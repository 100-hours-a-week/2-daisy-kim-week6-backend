package com.example.backend.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {
    @NotBlank(message = "답글 내용을 작성해 주세요.")
    private String content;

    public CommentRequestDto(String content) {
        this.content = content;
    }
}
