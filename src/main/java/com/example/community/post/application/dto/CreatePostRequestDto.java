package com.example.community.post.application.dto;

import com.example.community.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
