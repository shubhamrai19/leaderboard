package com.shubham.leaderboard.Service.impl;

import com.shubham.leaderboard.Model.Player;
import com.shubham.leaderboard.Repository.PlayerRepository;
import com.shubham.leaderboard.Service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(Player player) {
        try {
            playerRepository.save(player);
            return player;
        } catch (Exception e) {
            log.error("Error while creating player: {}", e.getMessage());
            throw new RuntimeException("Failed to create player");
        }
    }

    @Override
    public Player updatePlayer(Player player) {
        try {
            playerRepository.update(player);
            return player;
        } catch (Exception e) {
            log.error("Error while updating player: {}", e.getMessage());
            throw new RuntimeException("Failed to update player");
        }
    }

    @Override
    public void deletePlayer(int playerId) {
        try {
            playerRepository.delete(playerId);
        } catch (Exception e) {
            log.error("Error while deleting player with ID {}: {}", playerId, e.getMessage());
            throw new RuntimeException("Failed to delete player");
        }
    }

    @Override
    public Optional<Player> getPlayerId(int playerId) {
        try {
            return playerRepository.findById(playerId);
        } catch (Exception e) {
            log.error("Error while fetching player with ID {}: {}", playerId, e.getMessage());
            throw new RuntimeException("Failed to fetch player by ID");
        }
    }

    @Override
    public List<Player> getAllPlayer() {
        try {
            return playerRepository.findAll();
        } catch (Exception e) {
            log.error("Error while fetching all players: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch all players");
        }
    }
}
