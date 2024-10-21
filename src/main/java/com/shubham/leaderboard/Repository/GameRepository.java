package com.shubham.leaderboard.Repository;

import com.shubham.leaderboard.Model.Game;
import java.util.List;
import java.util.Optional;

public interface GameRepository {
    int save(Game game);
    int update(Game game);
    int delete(int gameId);
    Optional<Game> findById(int gameId);
    List<Game> findAll();
}
