package com.example.community.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UserInfoTest {

    @Test
    public void givenNameAndProfileImage_whenCreated_thenThrowNoting() throws Exception {
        // given
        String name ="abcd";
        String profileImage = "";

        // when & then 던지면 아무것도 발생하면 안된다
        assertDoesNotThrow(() -> new UserInfo(name, profileImage));

    }

    //이름이 빈값 일때
    @Test
    public void givenBlankNameAndProfileImage_whenCreated_thenThrowError() throws Exception {
        // given
        String name ="";
        String profileImage = "";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImage));
    }

}