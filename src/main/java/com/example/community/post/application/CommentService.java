package com.example.community.post.application;

import com.example.community.post.application.dto.CreateCommentRequestDto;
import com.example.community.post.application.dto.LikeRequestDto;
import com.example.community.post.application.dto.UpdateCommentRequestDto;
import com.example.community.post.application.interfaces.CommentRepository;
import com.example.community.post.application.interfaces.LikeRepository;
import com.example.community.post.domain.Post;
import com.example.community.post.domain.comment.Comment;
import com.example.community.user.application.UserService;
import com.example.community.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;

    private final CommentRepository commentRepository;

    private final PostService postService;

    private final LikeRepository likeRepository;

    // 댓글 정보 가지고 오기
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    // 댓글 생성
    public Comment createComment(CreateCommentRequestDto dto){
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post,user,dto.content());
        return commentRepository.save(comment);
    }

    // 댓글 수정
    public Comment updateComment(UpdateCommentRequestDto dto){
        Comment comment =getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto){
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment,user)){
            return;
        }

        comment.like(user);
        likeRepository.like(comment,user);
    }

    public void unlikeComment(LikeRequestDto dto){
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment,user)){
            comment.unlike();
            likeRepository.unlike(comment,user);
        }
    }


}
