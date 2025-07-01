package com.example.community.user.domain;

public class UserRelationCounter {
    private int count;

    public UserRelationCounter() {
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
}
