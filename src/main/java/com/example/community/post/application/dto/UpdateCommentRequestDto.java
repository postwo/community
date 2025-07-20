package com.example.community.post.application.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId,String content) {
}
