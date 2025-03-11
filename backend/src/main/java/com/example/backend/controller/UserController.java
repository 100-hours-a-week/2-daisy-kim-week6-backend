package com.example.backend.controller;

import com.example.backend.dto.*;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/registeration")
    public UserSigninResponseDto register(@RequestBody UserSigninRequestDto userSigninRequestDto) {
        return userService.signInUser(userSigninRequestDto);
    }

    //로그인
    @PostMapping("/authentication")
    public UserLoginResponseDto authentication(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.loginUser(userLoginRequestDto);
    }

    //탈퇴
    @DeleteMapping("/withdraw")
    public UserWithdrawResponseDto withdraw() {
        return userService.withdrawUser();
    }

    //개인 정보 조회
    @GetMapping("")
    public UserResponseDto getUser() {
        return userService.getUser();
    }

    //개인 정보 수정
    @PatchMapping("")
    public UserResponseDto editUser(@RequestBody UserSigninRequestDto userSigninRequestDto) {
        return userService.editUser(userSigninRequestDto);
    }

    //비밀번호 조회
    @GetMapping("/password")
    public UserPasswordResponseDto getPassword() {
        return userService.getUserPassword();
    }

    //비밀번호 수정
    @PatchMapping("/password")
    public UserPasswordResponseDto editPassword(@RequestBody UserSigninRequestDto userSigninRequestDto) {
        return userService.editUserPassword(userSigninRequestDto);
    }
}
