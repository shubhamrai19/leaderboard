package com.shubham.leaderboard.Repository.impl;

import com.shubham.leaderboard.Model.Game;
import com.shubham.leaderboard.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepositoryImpl implements GameRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Game> gameRowMapper = new RowMapper<>() {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Game(
                    rs.getInt("gameId"),
                    rs.getString("gameName"),
                    rs.getInt("maxPlayer"),
                    rs.getInt("maxDuration"),
                    rs.getTimestamp("createdAt"),
                    rs.getTimestamp("updatedAt")
            );
        }
    };

    @Override
    public int save(Game game) {
        String sql = "INSERT INTO Game (gameName, maxPlayer, maxDuration, createdAt) VALUES (?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, game.getGameName(), game.getMaxPlayer(), game.getMaxDuration(),new Timestamp(System.currentTimeMillis()));
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new RuntimeException("Game with name '" + game.getGameName() + "' already exists!");
            } else {
                throw new RuntimeException("An error occurred while saving the game: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the game: " + e.getMessage());
        }
    }

    @Override
    public int update(Game game) {
        String sql = "UPDATE Game SET gameName = ?, maxPlayer = ?, maxDuration = ?, updatedAt = ? WHERE gameId = ?";
        return jdbcTemplate.update(sql,
                game.getGameName(),
                game.getMaxPlayer(),
                game.getMaxDuration(),
                new Timestamp(System.currentTimeMillis()),
                game.getGameId());
    }

    @Override
    public int delete(int gameId) {
        String sql = "DELETE FROM Game WHERE gameId = ?";
        return jdbcTemplate.update(sql, gameId);
    }

    @Override
    public Optional<Game> findById(int gameId) {
        String sql = "SELECT * FROM Game WHERE gameId = ?";
        return jdbcTemplate.query(sql, gameRowMapper, gameId)
                .stream()
                .findFirst();
    }

    @Override
    public List<Game> findAll() {
        String sql = "SELECT * FROM Game";
        return jdbcTemplate.query(sql, gameRowMapper);
    }
}
