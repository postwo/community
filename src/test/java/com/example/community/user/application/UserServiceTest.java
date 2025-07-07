package com.example.community.user.application;

import com.example.community.user.application.dto.CreateUserRepositoryDto;
import com.example.community.user.application.interfaces.UserRepository;
import com.example.community.user.domain.User;
import com.example.community.user.domain.UserInfo;
import com.example.community.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private final UserRepository userRepository = new FakeUserRepository() ;

    private final UserService userService = new UserService(userRepository);

    @Test
    public void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // given
        CreateUserRepositoryDto dto = new CreateUserRepositoryDto("test", "");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getUserInfo();
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test",userInfo.getName());

    }



}