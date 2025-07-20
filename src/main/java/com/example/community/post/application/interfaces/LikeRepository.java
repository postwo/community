package com.example.community.post.application.interfaces;

import com.example.community.post.domain.Post;
import com.example.community.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);

    void like(Post post, User user);

    void unlike(Post post, User user);
}
