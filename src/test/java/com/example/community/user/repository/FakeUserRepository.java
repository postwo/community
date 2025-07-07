package com.example.community.user.repository;

import com.example.community.user.application.interfaces.UserRepository;
import com.example.community.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {

    private final Map<Long,User> store = new HashMap<>();

    // rdb에서 동작하는것처럼 이렇게 가짜로 구현
    @Override
    public User save(User user) {
        if (user.getId() != null){
            //null이 아니면 이미저장된 상태이니까 업데이트 처리
            //예를 들어 이미 저장된 유저의 이름이나 프로필 이미지가 바뀌었을 수도있기 때문에 업데이트 해주는거다
            store.put(user.getId(),user);
        }
        Long id = store.size() + 1L;
        User newUser =new User(id, user.getUserInfo());
        store.put(id,newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
