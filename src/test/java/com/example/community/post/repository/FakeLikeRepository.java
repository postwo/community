package com.example.community.post.repository;

import com.example.community.post.application.interfaces.LikeRepository;
import com.example.community.post.domain.Post;
import com.example.community.post.domain.comment.Comment;
import com.example.community.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FakeLikeRepository implements LikeRepository {

    // 하나의 게시글(Post)에 대해 여러 명의 유저(User)가 좋아요를 누를 수 있으므로
    // 중복 없는 Set<User> 구조로 관리함
    private final Map<Post, Set<User>> postLikes = new HashMap<>();
    private final Map<Comment,Set<User>> commentLikes = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        if (postLikes.get(post) == null){
            return false;
        }

        //이 게시글(post)을 좋아요 누른 유저 목록(Set<User>)에 지금 유저(user)가 포함돼 있는지 확인해서, 포함돼 있으면 true, 아니면 false를 반환
        return postLikes.get(post).contains(user);
    }

    @Override
    public void like(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if (users == null) {
           users = Set.of(user);
        }else {
            users.add(user);
        }
        postLikes.put(post,users);
    }

    @Override
    public void unlike(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if (users == null) {
            return;
        }
        users.remove(user);
        postLikes.put(post,users);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        if (commentLikes.get(comment)==null){
            return false;
        }
        //그 목록 안에 user가 존재하는지 확인한다
        return commentLikes.get(comment).contains(user);
    }

    @Override
    public void like(Comment comment, User user) {
        Set<User> users = commentLikes.get(comment);
        if (users == null){
            users = Set.of(user);
        }else {
            users.add(user);
        }
        commentLikes.put(comment,users);
    }

    @Override
    public void unlike(Comment comment, User user) {
        Set<User> users = commentLikes.get(comment);
        if (users == null){
            return;
        }
        users.remove(user);
        commentLikes.put(comment,users);
    }
}
