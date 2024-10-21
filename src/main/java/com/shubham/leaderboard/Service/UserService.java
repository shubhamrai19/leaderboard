package com.shubham.leaderboard.Service;

import com.shubham.leaderboard.Model.Score;
import com.shubham.leaderboard.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    User update(User user);

    void delete(int id);

    Optional<User> getId(int id);

    Optional<User> getByEmail(String email);

    List<User> getAllPlayer();
}
