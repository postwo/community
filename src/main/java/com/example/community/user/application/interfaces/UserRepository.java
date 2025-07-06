package com.example.community.user.application.interfaces;

import com.example.community.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
