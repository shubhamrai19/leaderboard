package com.shubham.leaderboard.Service;

import com.shubham.leaderboard.Model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    Game createGame(Game game);

    Game updateGame(Game game);
    void deleteGame(int gameId);

    Optional<Game> getGameById(int gameId);

    List<Game> getAllGames();
}
