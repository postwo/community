package com.example.community.post.repository;

import com.example.community.post.application.interfaces.CommentRepository;
import com.example.community.post.domain.comment.Comment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {

    private final Map<Long,Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        //댓글에 id가 이미 있으면 → 그대로 저장 (put)하고 반환
        if (comment.getId() != null){
            store.put(comment.getId(),comment);
            return comment;
        }

        // 이 코드를 작성한 이유는 아직 jpa를 등록 하지 않았다는 가장하에 id값이 없다고 하고 만든 코드
        //댓글에 id가 없으면 → 자동으로 id를 생성해서 새 댓글을 만들고 저장
        long id = store.size() +1;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(), comment.getContentObject());
        store.put(id,newComment);
        return newComment;

    }

    @Override
    public Optional<Comment> findById(Long id) {
        //Optional로 감싸서 → 댓글이 없을 경우 null이 아니라 빈 결과를 반환
        return Optional.ofNullable(store.get(id));
    }
}
