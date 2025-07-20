package com.example.community.post.application;

import com.example.community.post.application.dto.CreatePostRequestDto;
import com.example.community.post.application.dto.LikePostRequestDto;
import com.example.community.post.application.interfaces.LikeRepository;
import com.example.community.post.application.interfaces.PostRepository;
import com.example.community.post.domain.Post;
import com.example.community.user.application.UserService;
import com.example.community.user.domain.User;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PostService {

    private final UserService userService;

    private final PostRepository postRepository;

    private final LikeRepository likeRepository;

    //글 조회
    public Post getPost (Long id) {
        return postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Post not found"));
    }

    //글 생성
    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = Post.createPost(null,author,dto.content(),dto.state());
        return postRepository.save(post);
    }

    //글 수정
    public Post updatePost (Long id, CreatePostRequestDto dto) {
        Post post = getPost(id);
        User user = userService.getUser(dto.userId());

        post.updatePost(user,dto.content(),dto.state());
        return postRepository.save(post);
    }

    //like
    public void likePost(LikePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        // 좋아요를 이미 한번 했는데 또 눌러지는걸 방지
        if (likeRepository.checkLike(post,user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post,user);
    }

    //unlike
    public void unlikePost(LikePostRequestDto dto) {
        Post post = getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        // 좋아요 한개 있을경우 동작
        if (likeRepository.checkLike(post,user)) {
            post.unlike();
            likeRepository.unlike(post,user);
        }
    }


}
