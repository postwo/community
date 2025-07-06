package com.example.community.user.application;

import com.example.community.user.application.dto.CreateUserRepositoryDto;
import com.example.community.user.application.interfaces.UserRepository;
import com.example.community.user.domain.User;
import com.example.community.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 생성
    public User createUser(CreateUserRepositoryDto dto){
        UserInfo info = new UserInfo(dto.name(),dto.ProfileImageUrl());
        User user = new User(null,info);
        return userRepository.save(user);
    }

    // 유저 조회
    public User getUser(Long id){
        //orElseThrow(...)는 Optional에 값이 없을 경우 예외를 던진다
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
