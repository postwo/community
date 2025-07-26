package com.example.community.user.application;

import com.example.community.fake.FakeObjectFactory;
import com.example.community.user.application.dto.CreateUserRepositoryDto;
import com.example.community.user.application.dto.FollowUserRequestDto;
import com.example.community.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

    private User user1;
    private User user2;

    //user1과 user2는 항상 팔로잉 팔로우 상태가 동일해야한다
    private FollowUserRequestDto requestDto;


    //BeforeEach를 사용해서 매번(테스트마다) 객체를 새로 생성하게 만들어준다
    //각 테스트 메서드 실행 전에 반드시 실행되는 초기화 메서드를 지정하는 어노테이션
    @BeforeEach
    void init() {
        CreateUserRepositoryDto dto = new CreateUserRepositoryDto("test","");
        this.user1 =userService.createUser(dto);
        this.user2 =userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    //Follow : user1이 user2를 팔로우 하는 상태
    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(1,user1.followingCount());
        assertEquals(1,user2.followerCount());
    }

    //예외를 뱉는 테스트
    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError(){
        //given
        userRelationService.follow(requestDto); // 이미 한 번 팔로우

        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    // 자기 스스로를 팔로우 하는 테스트
    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError(){
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    //언팔로우
    @Test
    void givenCreateTwoUserFollow_whenUnFollow_thenUserUnFollowSaved() {
        //given
        userRelationService.follow(requestDto);

        //when
        userRelationService.unfollow(requestDto);

        //then
        assertEquals(0,user1.followingCount());
        assertEquals(0,user2.followerCount());
    }

    //예외를 뱉는 테스트
    @Test
    void givenCreateTwoUserFollowed_whenUnFollow_thenUserThrowError(){
        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    // 자기 스스로를 언팔로우 하는 테스트
    @Test
    void givenCreateOneUser_whenUnFollowSelf_thenUserThrowError(){
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when & then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }

}