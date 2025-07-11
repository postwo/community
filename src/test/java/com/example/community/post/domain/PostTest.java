package com.example.community.post.domain;

import com.example.community.post.domain.content.PostContent;
import com.example.community.post.domain.content.PostPublicationState;
import com.example.community.user.domain.User;
import com.example.community.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    //더미 유저
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L,info );
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_WhenLike_ThenLikeCountShouldBe1() {
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    //자기 자신을 포스트 눌렀을때 테스트
    @Test
    void givenPostCreated_WhenLikeByOtherUser_ThenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    //좋아요 취소를 정상적으로 처리했을때
    @Test
    void givenPostCreatedAndLike_WhenUnlike_ThenLikeCountShouldBe0() {
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    //좋아요를 누르지 않았을때 좋아요 취소했을경우 테스트
    @Test
    void givenPostCreated_WhenUnlike_ThenLikeCountShouldBe0() {
        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }


    @Test
    void givenPostCreated_WhenUpdateContent_ThenContentShouldBeUpdated() {
        // given
        String newPostContent = "new content";

        // when
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);

        // then
        assertEquals(newPostContent, post.getContent());
    }

    //다른 유저의 포스트를 수정안되는지 테스트
    @Test
    void givenPostCreated_WhenUpdateContentByOtherUser_ThenThrowException() {
        // given
        String newPostContent = "new content";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
    }
}