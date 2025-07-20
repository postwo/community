package com.example.community.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {
}
