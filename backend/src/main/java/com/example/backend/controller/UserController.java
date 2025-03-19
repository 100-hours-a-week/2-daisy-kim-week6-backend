package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.user.*;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/registeration")
    public UserSigninResponseDto register(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("passwordConfirm") String passwordConfirm,
            @RequestParam("email") String email,
            @RequestPart(value = "imageUrl", required = false) MultipartFile imageUrl
    ) throws IOException {
        // 이미지 저장 경로 변환
        String imagePath = null;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String uploadDir = "/Users/kdh/Desktop/hw_image/"; // 저장 폴더
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs(); // 디렉토리 생성
            }

            // 파일명 랜덤 값 설정 후 저장
            String fileName = UUID.randomUUID() + "_" + imageUrl.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            imageUrl.transferTo(destination);

            // DB에 저장할 파일 경로
            imagePath = uploadDir + fileName;
        }

        // DTO 변환
        UserSigninRequestDto dto = new UserSigninRequestDto(name, password, passwordConfirm, email, imagePath);
        return userService.signInUser(dto);
    }


    //로그인
    @PostMapping(value = "/authentication")
    public UserLoginResponseDto authentication(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.loginUser(userLoginRequestDto);
    }

    //탈퇴
    @DeleteMapping("/withdraw")
    public ResponseDto withdraw() {
        return userService.withdrawUser();
    }

    @PostMapping("/logout")
    public ResponseDto logout() {
        return userService.logoutUser();
    }

    //개인 정보 조회
    @GetMapping("")
    public UserResponseDto getUser() {
        return userService.getUser();
    }

    //개인 정보 수정
    @PatchMapping("")
    public UserResponseDto editUser(
            @RequestPart(value = "name", required = false) String name,
            @RequestPart(value = "imageUrl", required = false) MultipartFile imageUrl)
            throws IOException {

        String imagePath = null;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String uploadDir = "/Users/kdh/Desktop/hw_image/"; // 저장 폴더
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs(); // 디렉토리 생성
            }

            // 파일명 랜덤 값 설정 후 저장
            String fileName = UUID.randomUUID() + "_" + imageUrl.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            imageUrl.transferTo(destination);

            // DB에 저장할 파일 경로
            imagePath = uploadDir + fileName;
        }
        UserSigninRequestDto dto = UserSigninRequestDto.builder()
                .name(name)
                .imageUrl(imagePath)
                .build();
        return userService.editUser(dto);
    }

    //비밀번호 수정
    @PatchMapping("/password")
    public ResponseDto editPassword(@RequestBody UserSigninRequestDto userSigninRequestDto) {
        UserSigninRequestDto dto = UserSigninRequestDto.builder()
                .password(userSigninRequestDto.getPassword())
                .passwordConfirm(userSigninRequestDto.getPasswordConfirm())
                .build();

        return userService.editUserPassword(dto);
    }
}
