package com.shubham.leaderboard.Service.impl;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Repository.GameRepository;
import com.shubham.leaderboard.Service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(Game game) {
        gameRepository.save(game);
        return game;
    }

    @Override
    public Game updateGame(Game game) {
        gameRepository.update(game);
        return game;
    }

    @Override
    public void deleteGame(int gameId) {
        gameRepository.delete(gameId);
    }

    @Override
    public Optional<Game> getGameById(int gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}
