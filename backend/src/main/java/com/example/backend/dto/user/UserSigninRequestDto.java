package com.example.backend.dto.user;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSigninRequestDto {
    private String name;
    private String password;
    private String passwordConfirm;
    private String email;
    @Setter
    private String imageUrl;
}
