package com.example.community.common.domain;

// 크게 변경사항이 없을거 같은 경우는 이렇게 공통으로 뽑아주는게 좋다
public class PositiveIntegerCounter {
    private int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public void increase(){
        this.count++;
    }

    public void decrease(){
        //count가 0보다 작다면 빈값을 return
        if (count <= 0){
            return;
        }
        this.count--;
    }

    public int getCount() {
        return count;
    }
}
