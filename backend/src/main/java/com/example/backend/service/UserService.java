package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.user.*;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, HttpSession httpSession, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입
    public UserSigninResponseDto signInUser(UserSigninRequestDto userSigninRequestDto) {
        Optional<User> sameEmail = userRepository.findByEmail(userSigninRequestDto.getEmail());
        if (sameEmail.isPresent()) {
            return new UserSigninResponseDto(null, "중복된 이메일입니다.");
        }
        if (userSigninRequestDto.getPassword() == null || userSigninRequestDto.getPassword().isEmpty()) {
            return new UserSigninResponseDto(null, "비밀번호를 입력해 주세요.");
        }
        if (userSigninRequestDto.getPassword().length() < 8 || userSigninRequestDto.getPassword().length() > 20) {
            return new UserSigninResponseDto(null, "비밀번호는 8자 이상 20자 이하여야 합니다.");
        }
        if (userSigninRequestDto.getPasswordConfirm() == null || userSigninRequestDto.getPasswordConfirm().isEmpty()) {
            return new UserSigninResponseDto(null, "비밀번호를 한 번 더 입력해 주세요.");
        }
        if (!userSigninRequestDto.getPassword().equals(userSigninRequestDto.getPasswordConfirm())) {
            return new UserSigninResponseDto(null, "비밀번호와 다릅니다.");
        }
        User newUser = new User(
                userSigninRequestDto.getName(),
                passwordEncoder.encode(userSigninRequestDto.getPassword()),
                userSigninRequestDto.getEmail(),
                userSigninRequestDto.getImageUrl()
        );
        User savedNewUser = userRepository.save(newUser);
        return new UserSigninResponseDto(savedNewUser.getId(), "회원 가입 성공");
    }

    //로그인
    public UserLoginResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        Optional<User> userCheck = userRepository.findByEmail(userLoginRequestDto.getEmail());
        if (userCheck.isPresent()) {
            User user = userCheck.get();
            if(passwordEncoder.matches(userLoginRequestDto.getPassword(),user.getPassword())) {
                httpSession.setAttribute("userId", user.getId());
                httpSession.setAttribute("user", user);
                return new UserLoginResponseDto(user.getId(), "로그인 성공");
            }
        }
        return new UserLoginResponseDto(null, "아이디 또는 비밀번호를 확인해 주세요.");
    }

    //탈퇴
    public ResponseDto withdrawUser() {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if(userId == null) {
            return new ResponseDto("로그인하지 않은 유저는 탈퇴할 수 없습니다.");
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            userRepository.deleteById(userId);
            return new ResponseDto("회원 탈퇴에 성공하셨습니다.");
        }
        return new ResponseDto("회원 탈퇴에 실패하셨습니다.");
    }

    //개인 정보 수정 시 불러오는 정보
    public UserResponseDto getUser() {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if(userId == null) {
            return new UserResponseDto("로그인하지 않은 유저는 정보를 불러올 수 없습니다.");
        }
        Optional<User> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()) {
            User user = userEntity.get();
            return new UserResponseDto(user.getName(), user.getEmail(), user.getImageUrl(), "개인 정보 불러오기 성공");
        }
        return new UserResponseDto("회원 정보 불러오기 실패");
    }

    //개인 정보 수정
    public UserResponseDto editUser(UserSigninRequestDto userSigninRequestDto) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId == null) {
            return new UserResponseDto("로그인하지 않은 유저는 회원 정보를 수정할 수 없습니다.");
        }
        Optional<User> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            User user = userEntity.get();
            if (userSigninRequestDto.getName() == null || userSigninRequestDto.getName().isEmpty()) {
                return new UserResponseDto("닉네임을 입력해 주세요.");
            } else if (userSigninRequestDto.getName().length() >= 11) {
                return new UserResponseDto("닉네임은 최대 10자까지 작성 가능합니다.");
            } else {
                user.setName(userSigninRequestDto.getName());
            }
            if (userSigninRequestDto.getImageUrl() == null || userSigninRequestDto.getImageUrl().isEmpty()) {
                return new UserResponseDto("프로필 이미지를 등록해 주세요.");
            } else {
                user.setImageUrl(userSigninRequestDto.getImageUrl());
            }
            userRepository.save(user);
            return new UserResponseDto("회원 정보 수정 성공하였습니다.");
        }
        return new UserResponseDto("회원 정보 수정 실패");
    }

    //비밀번호 수정
    public ResponseDto editUserPassword(UserSigninRequestDto userSigninRequestDto) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if(userId == null) {
            return new ResponseDto("로그인하지 않은 사용자는 비밀번호를 수정할 수 없습니다.");
        }
        Optional<User> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent()) {
            User user = userEntity.get();
            if (userSigninRequestDto.getPassword() == null || userSigninRequestDto.getPassword().isEmpty()) {
                return new ResponseDto("비밀번호를 입력해 주세요.");
            }
            if (userSigninRequestDto.getPassword().length() < 8 || userSigninRequestDto.getPassword().length() > 20) {
                return new ResponseDto("비밀번호는 8자 이상 20자 이하여야 합니다.");
            }
            if (userSigninRequestDto.getPasswordConfirm() == null || userSigninRequestDto.getPasswordConfirm().isEmpty()) {
                return new ResponseDto("비밀번호를 한 번 더 입력해 주세요.");
            }
            if (!userSigninRequestDto.getPassword().equals(userSigninRequestDto.getPasswordConfirm())) {
                return new ResponseDto("비밀번호와 다릅니다.");
            }
            user.setPassword(passwordEncoder.encode(userSigninRequestDto.getPassword()));
            userRepository.save(user);
            return new ResponseDto("비밀번호 수정 성공");
        }
        return new ResponseDto("비밀번호 수정 실패");
    }
}
