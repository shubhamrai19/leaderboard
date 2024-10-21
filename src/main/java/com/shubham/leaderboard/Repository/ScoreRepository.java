package com.shubham.leaderboard.Repository;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Model.Score;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository {

    int save(Score score);

    int update(Score score);

    int delete(int scoreId);

    Optional<Score> findById(int scoreId);

    List<Score> findAll();
}
