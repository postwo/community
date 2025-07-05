package com.example.community.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);

    @Test
    public void givenTwoUser_whenEqual_thenReturnFalse() {
        // when
        boolean isSame = user1.equals(user2);

        // then
        assertFalse(isSame);
    }

    // 같은 id가 주어졌을때 같은지
    @Test
    public void givenTwoSameIdUser_whenEqual_thenReturnTrue() {
        // given
        User sameUser = new User(1L, userInfo);

        // when
        boolean isSame = user1.equals(sameUser);

        // then
        assertTrue(isSame);
    }

    @Test
    public void givenTwoUser_whenHashcode_thenNotEqual() {
        // when
        int hashcode1 =user1.hashCode();
        int hashcode2 =user2.hashCode();

        // then
        assertNotEquals(hashcode1, hashcode2);

    }

    @Test
    public void givenTwoSameIdUser_whenHashcode_thenEqual() {
        // given
        User sameUser = new User(1L, userInfo);

        // when
        int hashcode1 =user1.hashCode();
        int sameUserHashcode =sameUser.hashCode();

        // then
        assertEquals(hashcode1, sameUserHashcode);
    }

}
