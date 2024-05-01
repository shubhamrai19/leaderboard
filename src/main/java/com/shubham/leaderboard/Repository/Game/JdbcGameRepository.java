package com.shubham.leaderboard.Repository.Game;

import com.shubham.leaderboard.Model.Game;

public interface JdbcGameRepository {
    int saveGame(Game game);
    Game byGameByID(int id);

    Game byGameByName(String name);

}
