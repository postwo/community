package com.example.community.domain;

//이게 vo 이다
public class UserInfo {

    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {

        // 이름이 비었는지 확인(유효성 검사)
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
