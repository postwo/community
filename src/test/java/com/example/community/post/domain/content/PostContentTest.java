package com.example.community.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOkWhenCreatePostContentThenReturnTextContext() {
        // given
        String contentText = "this is a test content";

        // when
        PostContent content = new PostContent(contentText);

        // then
        assertEquals(contentText, content.getContentText());
    }

    //에러 처리
    @Test
    void givenContentLengthIsOverLimitCreatePostContentThenThrowError() {
        // given
        //"a"라는 글자를 501번 반복해서 content라는 문자열을 만든다
        //repeat는 자바 11부터 지원
        // 501을 선택한 이유는 테스트를 만들때 에는 경계값을 최대한 많이 테스트에 넣는게 중요하기 때문에
        String content = "a".repeat(501);//repeat는 반복문의 결과와 유사한 효과를 간편하게 제공

        // when, then
        // new PostContent(content)를 실행했을 때 실제 발생한 예외가 IllegalArgumentException 클래스와 타입이 같은지 확인하는 테스트 코드이다
        // "해당 코드 실행 중에 예외가 발생해야 하고, 그 예외의 클래스가 IllegalArgumentException와 정확히 같은 타입이어야 테스트 성공!"
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    //여러 가지 입력값으로 같은 테스트 메서드를 반복 실행할 수 있게 해주는 어노테이션
    //@Test는 1번 실행되지만, @ParameterizedTest는 파라미터마다 반복 실행됨
    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverAndKorean_whenCreated_ThenThrowError(String koreanContent) {//koreanContent에ValueSource값이 들어간다
        // given
        String content = koreanContent.repeat(4);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_ThenThrowError() {
        // given
        String content = "abcd";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource //null과 빈 문자열 "" 두 개를 자동으로 추가해서 content에 들어간다
    void givenContentIsEmpty_whenCreated_ThenThrowError(String content) {
        //when then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsEmpty_whenUpdated_thenNotThrowError(){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when&then
        postContent.updateContent("this is a updated content");
    }

    @Test
    void givenContentLengthIsOk_WhenUpdated_ThenNotThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when
        String newContent = "this is an other test content";
        postContent.updateContent(newContent);

        // then
        assertEquals(newContent, postContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_WhenUpdated_ThenThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String overLimitContent = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(overLimitContent));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverAndKorean_whenUpdated_ThenThrowError(String koreanContent) {//koreanContent에ValueSource값이 들어간다
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String value = koreanContent.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenContentLengthIsOverAndKorean_whenUpdated_ThenThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String value = "a".repeat(4);
        assertThrows(IllegalArgumentException.class, () ->postContent.updateContent(value));
    }

}


