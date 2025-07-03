package com.example.community.post.domain.content;

public enum PostPublicationState {
    PUBLIC, // 전체 공개
    ONLY_FOLLOWER, // 팔로워만 공개
    PRIVATE // 나만 볼수 있다 비공개
}
