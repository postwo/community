package com.example.community.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeInfoTest {

    // 여기다가 sleep을 준이유는
    //자바에서 LocalDateTime.now()가 너무 빠르게 실행되어 수정전 시간 값과 수정 후 시간이 같아 버리기때문이다
    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
        // given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = datetimeInfo.getDateTime();

        // when
        Thread.sleep(1); // 1ms 잠시 대기
        datetimeInfo.updateEditDatetime();

        // then
        assertTrue(datetimeInfo.isEdited());
        assertNotEquals(localDateTime, datetimeInfo.getDateTime());
    }
}
