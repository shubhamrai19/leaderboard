package com.shubham.leaderboard.service;

import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.Repository.Player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player savePlayer(Player player) {
        playerRepository.savePlayer(player);
        return playerRepository.get(player.name());
    }


    public Player getBy(int id) {
        return playerRepository.getByID(id);
    }

    public Player get(String name) {
        return playerRepository.get(name);
    }


}
