package com.shubham.leaderboard.service;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.Repository.Game.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameService {
    @Value("${myname}")
    private String myName;
    @Autowired
    GameRepository gameRepository;

    public Game saveGame(Game game) {
        gameRepository.saveGame(game);
       log.info(myName);

        return gameRepository.byGameByName(game.name());
    }


    public Game getByID(int id) {
        return gameRepository.byGameByID(id);
    }
}
