package com.shubham.leaderboard.Repository;

import com.shubham.leaderboard.Model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {

    int save(Player player);

    int update(Player player);

    int delete(int playerId);

    Optional<Player> findById(int playerId);

    List<Player> findAll();
}
