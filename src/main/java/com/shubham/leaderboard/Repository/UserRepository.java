package com.shubham.leaderboard.Repository;

import com.shubham.leaderboard.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    int save(User user);

    int update(User user);

    int delete(int userId);

    Optional<User> findById(int userId);

    List<User> findAll();

    Optional<User> findByEmail(String email);
}

