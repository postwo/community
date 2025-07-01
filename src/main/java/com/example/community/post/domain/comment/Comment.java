package com.example.community.post.domain.comment;

import com.example.community.common.domain.PositiveIntegerCounter;
import com.example.community.post.domain.Post;
import com.example.community.post.domain.content.Content;
import com.example.community.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content contet;
    private final PositiveIntegerCounter likeCount;

    public Comment (Long id, Post post, User author, Content contet, PositiveIntegerCounter likeCount) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        if (post == null) {
            throw new IllegalArgumentException();
        }

        if (contet == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.contet = contet;
        this.likeCount = new PositiveIntegerCounter(); // 0으로 초기화 해주기 위해 객체 생성
    }

    public void like(User user) {
        if (this.author.equals(user)) { // 본인을 본인이 좋아요 누르는걸 방지
            throw new IllegalArgumentException();
        }

        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }


}
