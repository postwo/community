package com.example.community.post.repository;

import com.example.community.post.application.interfaces.PostRepository;
import com.example.community.post.domain.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePostRepository implements PostRepository {
    private final Map<Long,Post> store = new HashMap<>();

    @Override
    public Post save(Post pos) {
        if (pos.getId() != null) {
            store.put(pos.getId(),pos);
            return pos;
        }
        long id = store.size() +1;
        Post newPost = new Post(id, pos.getAuthor(), pos.getContentObject());
        store.put(id,newPost);
        return newPost;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
