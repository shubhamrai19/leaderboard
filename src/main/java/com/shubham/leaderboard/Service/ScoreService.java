package com.shubham.leaderboard.Service;

import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.Model.Score;

import java.util.List;
import java.util.Optional;

public interface ScoreService {
    Score create(Score score);

    Score update(Score score);

    void delete(int id);

    Optional<Score> getId(int id);

    List<Score> getAllPlayer();
}
