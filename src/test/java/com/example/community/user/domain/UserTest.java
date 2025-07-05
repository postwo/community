package com.example.community.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1;
    private User user2 ;

    //위를 그대로 사용하면 팔로우 테스트 취소 테스트 이렇게 두번할경우
    //팔로우 테스트할때 내역이 남을수 있다 그러므로 BeforeEach를 사용한다 , 팔로우와 팔로워수가 0으로 초기화
    //BeforeEach를 사용해서 매번(테스트마다) 객체를 새로 생성하게 만들어준다
    //각 테스트 메서드 실행 전에 반드시 실행되는 초기화 메서드를 지정하는 어노테이션
    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

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

    // 팔로우
    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount()  {

        // when
        user1.follow(user2);

        // then
        assertEquals(1, user1.followingCount()); // 내가 팔로잉한 카운트
        assertEquals(0, user1.followerCount()); // 나를 팔로우한 사람이 늘어나면 안되다 왜냐 나는 팔로잉 만 했기 때문에
        assertEquals(0,user2.followingCount());
        assertEquals(1,user2.followerCount()); // user1이 나를 팔로우 했으니까 user2의 팔로우 증가
    }

    //un팔로우
    @Test
    void givenTwoUser1FollowUser2_whenUnfollow_thenDecreaseUserCount()  {
        //given
        user1.follow(user2);

        // when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0,user2.followingCount());
        assertEquals(0,user2.followerCount());
    }

    //디크리즈 되었을때 0이하가 되는지 테스트
    @Test
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount()  {
        //when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(0,user2.followingCount());
        assertEquals(0,user2.followerCount());
    }

}
