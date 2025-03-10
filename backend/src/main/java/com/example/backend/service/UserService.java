package com.example.backend.service;

import com.example.backend.dto.UserDto;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Dto로 변경
    private UserDto convertUserToUserDto(User user) {
        return new UserDto(user.getUserId(), user.getUserName(), user.getUserPassword(), user.getUserEmail(), user.getUserProfileImgUrl());
    }


}
