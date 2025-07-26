package com.example.community.user.application;

import com.example.community.fake.FakeObjectFactory;
import com.example.community.user.application.dto.CreateUserRepositoryDto;
import com.example.community.user.domain.User;
import com.example.community.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();

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