package com.example.community.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveIntegerCounterTest {

    @Test
    void givenCreated_whenIncrease_thenCountIsOne() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when : 1증가
        counter.increase();

        // then = 1하고 증가한 값인 count가 같은지 비교
        assertEquals(1, counter.getCount());
    }

    @Test
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero() throws Exception {
         // given
         PositiveIntegerCounter counter = new PositiveIntegerCounter();
         counter.increase();

         // when
         counter.decrease();

         // then
         assertEquals(0, counter.getCount());
    }

    @Test // 디크리즈 할때 0이하로 안떨어지는지 체크
    void givenCreated_whenDecrease_thenCountIsZero() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount());
    }





}