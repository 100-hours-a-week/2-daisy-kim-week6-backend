package com.example.backend.dto.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardPostRequestDto {
    @NotBlank(message = "제목을 작성해 주세요.")
    private String boardTitle;
    @NotBlank(message = "내용을 작성해 주세요.")
    private String boardContent;
    private String boardContentImgUrl;
}
