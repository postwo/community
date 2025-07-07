package com.example.community.user.application;

import com.example.community.user.application.dto.FollowUserRequestDto;
import com.example.community.user.application.interfaces.UserRelationRepository;
import com.example.community.user.domain.User;

public class UserRelationService {
    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService, UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto){
        User user =userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        // 팔로우 한 상태이면 예외 처리
        if (userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException();
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unfollow(FollowUserRequestDto dto){
        User user =userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        //팔로우 안 한 상태라면 예외 처리
        if (!userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException();
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }
}

