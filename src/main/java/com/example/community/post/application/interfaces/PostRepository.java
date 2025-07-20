package com.example.community.post.application.interfaces;

import com.example.community.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post pos);

    Optional<Post> findById(Long id);
}
