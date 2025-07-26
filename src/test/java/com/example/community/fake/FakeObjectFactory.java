package com.example.community.fake;

import com.example.community.post.application.CommentService;
import com.example.community.post.application.PostService;
import com.example.community.post.application.interfaces.CommentRepository;
import com.example.community.post.application.interfaces.LikeRepository;
import com.example.community.post.application.interfaces.PostRepository;
import com.example.community.post.repository.FakeCommentRepository;
import com.example.community.post.repository.FakeLikeRepository;
import com.example.community.post.repository.FakePostRepository;
import com.example.community.user.application.UserRelationService;
import com.example.community.user.application.UserService;
import com.example.community.user.application.interfaces.UserRelationRepository;
import com.example.community.user.application.interfaces.UserRepository;
import com.example.community.user.repository.FakeUserRelationRepository;
import com.example.community.user.repository.FakeUserRepository;

// 싱글톤
public class FakeObjectFactory {

    private static final UserRepository fakeuserRepository = new FakeUserRepository();
    private static final UserRelationRepository  fakeuserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository   fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeuserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeuserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, fakeCommentRepository, postService,fakeLikeRepository);

    //private으로 외부에서 FakeObjectFactory 인스턴스를 선언하지 못하게 한다
    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }


}
