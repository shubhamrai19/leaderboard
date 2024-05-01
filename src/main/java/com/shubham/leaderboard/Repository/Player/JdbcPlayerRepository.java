package com.shubham.leaderboard.Repository.Player;

import com.shubham.leaderboard.Model.Player;


public interface JdbcPlayerRepository {
    int savePlayer(Player player);


    Player getByID(int id);

    Player get(String name);

}
