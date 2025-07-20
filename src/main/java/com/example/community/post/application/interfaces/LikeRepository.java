package com.example.community.post.application.interfaces;

import com.example.community.post.domain.Post;
import com.example.community.post.domain.comment.Comment;
import com.example.community.user.domain.User;

public interface LikeRepository {
    // 게시글
    boolean checkLike(Post post, User user);

    void like(Post post, User user);

    void unlike(Post post, User user);


    // 댓글
    boolean checkLike(Comment comment, User user);

    void like(Comment comment, User user);

    void unlike(Comment comment, User user);
}
