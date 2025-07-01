package com.example.community.post.domain.comment;

import com.example.community.post.domain.Post;
import com.example.community.post.domain.content.Content;
import com.example.community.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User autor;
    private final Content contet;

    public Comment (Long id, Post post, User autor, Content contet) {
        if (autor == null) {
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
        this.autor = autor;
        this.contet = contet;
    }




}
