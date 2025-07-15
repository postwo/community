package com.example.community.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_WhenCreateCommentContent_ThenReturnTextContext() {
        // given
        String contentText = "this is a test content";

        //when
        CommentContent content = new CommentContent(contentText);

        //then
        assertEquals(contentText,content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowError() {
        //given
        String content = "a".repeat(101);

        //when,then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverLimitAndKorean_whenCreateCommentContent_ThenThrowError(String koreanContent) {
        // given
        String content = koreanContent.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyAndNull_whenCreateCommentContent_ThenThrowError(String content) {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

}
