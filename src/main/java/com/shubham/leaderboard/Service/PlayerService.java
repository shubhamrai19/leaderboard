package com.shubham.leaderboard.Service;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Player createPlayer(Player player);

    Player updatePlayer(Player player);

    void deletePlayer(int playerId);

    Optional<Player> getPlayerId(int playerId);

    List<Player> getAllPlayer();
}
