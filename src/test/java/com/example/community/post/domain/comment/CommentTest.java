package com.example.community.post.domain.comment;

import com.example.community.post.domain.Post;
import com.example.community.post.domain.content.CommentContent;
import com.example.community.post.domain.content.PostContent;
import com.example.community.user.domain.User;
import com.example.community.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//테스트는 작은 단위부터(like,unlike,update 이렇게 세부적으로 작은 단위)
class CommentTest {


    //더미 유저
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L,info );
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("content"));

    //다른 사용자가 내가 쓴 댓글에 ‘좋아요’를 눌렀을때 테스트
    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        // given
        comment.like(otherUser);

        // when
        assertEquals(1, comment.getLikeCount());
    }

    // 자기자신이 글에 좋아요를 눌렀을때 에러 테스트
    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowException() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
         // given
        comment.like(otherUser);

         // when
        comment.unlike();

         // then
        assertEquals(0,comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateContent_thenShouldBeUpdated() {
        // given
        String newCommentContent = "new content";

        // when
        comment.updateComment(user, newCommentContent);

        // then
        assertEquals(newCommentContent, comment.getContet());
    }

    //댓글 내용이 100자를 초과하면 예외가 발생
    @Test
    void givenComment_whenUpdateContentOver100_thenThrowException() {
        // given
        String newCommentContent = "a".repeat(101);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newCommentContent));
    }

}